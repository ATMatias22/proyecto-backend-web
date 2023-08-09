package com.sensor.service.implementation;

import com.sensor.dao.ICartDao;
import com.sensor.entity.Cart;
import com.sensor.entity.CartProduct;
import com.sensor.enums.CartState;
import com.sensor.exception.GeneralException;
import com.sensor.pattern.cart.strategy.CartStateStrategy;
import com.sensor.pattern.cart.strategy.CartStateStrategyFactory;
import com.sensor.security.MainUser;
import com.sensor.security.entity.User;
import com.sensor.security.service.IUserService;
import com.sensor.service.ICartService;
import com.sensor.utils.directory.DirectoryData;
import com.sensor.utils.file.FileHelper;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import com.sensor.utils.transport.cart.CartInfoTransportToService;
import com.sensor.utils.transport.cart.CartTransportToController;
import com.sensor.utils.transport.cartProduct.CartProductTransportToController;
import com.sensor.utils.transport.product.ProductTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartDao cartDao;
    @Autowired
    private IUserService userService;
    @Autowired
    private CartStateStrategyFactory cartStateStrategyFactory;


    @Override
    public CartInfoTransportToController getCartThatAreNotTerminadoOrEntregaByUserLoggedIn() {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

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
    public void saveCart(Cart cart) {
        this.cartDao.saveCart(cart);
    }

    @Override
    @Transactional
    public CartInfoTransportToController changeState(CartInfoTransportToService cartInfoTransportToService) {


        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

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
    public CartProduct addProduct(Long idProduct, Double quantity) {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        return strategy.addProduct(idProduct,quantity,userLoggedIn,cart);
    }

    @Override
    @Transactional
    public CartProduct removeProduct(Long idProduct, Double quantity) {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        return strategy.removeProduct(idProduct,quantity,userLoggedIn,cart);
    }

    @Override
    public List<Cart> getAllCartsWhereTheStatusIsTerminadoByUserLoggedIn() {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        return this.cartDao.getAllCartsByUserAndWhereTheStatusIsTerminado(userLoggedIn);
    }

    @Override
    public List<Cart> getAllCartsWhereTheStatusIsEntregaByUserLoggedIn() {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        return this.cartDao.getAllCartsByUserAndWhereTheStatusIsEntrega(userLoggedIn);
    }

    @Override
    public void cancelCart() {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        Cart cart = this.cartDao.getCartByUser(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        strategy.cancel(cart);
    }


}
