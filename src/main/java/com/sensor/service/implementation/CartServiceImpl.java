package com.sensor.service.implementation;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;
import com.sensor.dao.ICartDao;
import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.entity.Product;
import com.sensor.entity.Stock;
import com.sensor.enums.CartState;
import com.sensor.enums.StockState;
import com.sensor.exception.GeneralException;
import com.sensor.external.dto.webhook.MercadoPagoWebhookDTO;
import com.sensor.pattern.cart.strategy.CartStateStrategy;
import com.sensor.pattern.cart.strategy.CartStateStrategyFactory;
import com.sensor.security.entity.User;
import com.sensor.service.ICartService;
import com.sensor.service.IStockService;
import com.sensor.utils.directory.DirectoryData;
import com.sensor.utils.file.FileHelper;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import com.sensor.utils.transport.cartProduct.CartProductTransportToController;
import com.sensor.utils.transport.product.ProductTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartDao cartDao;
    @Autowired
    private CartStateStrategyFactory cartStateStrategyFactory;

    @Autowired
    private IStockService stockService;

    @Value("${MP}")
    private String accessToken; // Coloca tu token de acceso aquí



    @Override
    public CartInfoTransportToController getCartByUserLoggedIn(User userLoggedIn) {

        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        List<CartProductTransportToController> cartProductsTransport = new ArrayList<>();

        cart.getCartProducts().forEach(cp -> {
            String pathFile = cp.getProduct().getImage() != null ? DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES + cp.getProduct().getImage() : null;
            ProductTransportToController productTransportToController = new ProductTransportToController(cp.getProduct(), FileHelper.filePathToBase64String(pathFile, DirectoryData.PRODUCT_DEFAULT_IMAGE));
            cartProductsTransport.add(new CartProductTransportToController(productTransportToController, cp.getQuantity(), cp.getCreated()));
        });

        CartTransportToController cartTransport = new CartTransportToController();
        cartTransport.setCart(cart);
        cartTransport.setProducts(cartProductsTransport);

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        return strategy.getCartInfo(userLoggedIn, cartTransport);
    }

    @Override
    @Transactional
    public CartInfoTransportToController changeState(CartInfoTransportToService cartInfoTransportToService, User userLoggedIn) {




        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        strategy.verifyNecessaryData(cartInfoTransportToService);

        List<CartProductTransportToController> cartProductsTransport = new ArrayList<>();

        cart.getCartProducts().forEach(cp -> {
            String pathFile = cp.getProduct().getImage() != null ? DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES + cp.getProduct().getImage() : null;
            ProductTransportToController productTransportToController = new ProductTransportToController(cp.getProduct(), FileHelper.filePathToBase64String(pathFile, DirectoryData.PRODUCT_DEFAULT_IMAGE));
            cartProductsTransport.add(new CartProductTransportToController(productTransportToController, cp.getQuantity(), cp.getCreated()));
        });

        CartTransportToController cartTransport = new CartTransportToController();
        cartTransport.setCart(cart);
        cartTransport.setProducts(cartProductsTransport);


        return strategy.changeState(userLoggedIn, cartTransport, cart, cartInfoTransportToService);
    }

    @Override
    @Transactional
    public CartProduct addProduct(Long idProduct, int quantity, User userLoggedIn) {



        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        return strategy.addProduct(idProduct, quantity, userLoggedIn, cart);
    }

    @Override
    @Transactional
    public CartProduct removeProduct(Long idProduct, int quantity, User userLoggedIn) {

        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        return strategy.removeProduct(idProduct, quantity, userLoggedIn, cart);
    }


    @Override
    public void cancelCart(User userLoggedIn) {



        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        strategy.cancel(cart);
    }

    @Override
    public String getPreferenceId(User userLoggedIn) {


        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();

        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        return strategy.getPreferenceId(cart);
    }


    @Override
    public void saveCart(Cart cart) {
        this.cartDao.saveCart(cart);
    }

    @Override
    public void preferenceNotification(MercadoPagoWebhookDTO mercadoPagoWebhookDTO) {

        Long cartId = null;

        MercadoPagoConfig.setAccessToken(accessToken);

        String idData = mercadoPagoWebhookDTO.getData().getId();

        System.out.println("idData: " + idData);

        try {

            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(Long.parseLong(idData));

            if(!payment.getStatus().equals("approved")){
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "El pago no fue aprobado");
            }


            Map<String, Object> map = payment.getMetadata();

            if(!map.containsKey("cart_id")){
                System.out.println("No se encontro el atributo cartId");
                throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas al recibir las notificaciones");
            }


            //los valores los toma como tipo double, y yo necesito Long por eso esta conversion.
            Double parsedCartId = Double.parseDouble(map.get("cart_id").toString());
            cartId = parsedCartId.longValue();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            throw new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, "Problemas al recibir las notificaciones");
        }


        Cart cart = this.cartDao.getCartById(cartId).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este pago "));

        User userLoggedIn = cart.getUser();

        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(cart.getState());

        strategy.preferenceNotification(cart, userLoggedIn);

    }

    @Override
    @Transactional
    public void deleteCart(User userLoggedIn) {

        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se puede borrar el carrito porque no existe"));

        cart.getCartProducts().forEach( cartProduct -> {
            Product product = cartProduct.getProduct();
            int quantity = cartProduct.getQuantity();
            List<Stock> stocks = this.stockService.getNAvailableStockQuantityByProductAndCart(product, cart, quantity);
            stocks.forEach( stock -> {
                stock.setStockState(StockState.DISPONIBLE);
                stock.setCart(null);
            });
            //guardamos para actualizar el stock
            this.stockService.saveStockIterable(stocks);
        });



        this.cartDao.deleteCart(cart);
    }


}
