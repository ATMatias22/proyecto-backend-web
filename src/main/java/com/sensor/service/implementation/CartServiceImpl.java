package com.sensor.service.implementation;

import com.sensor.dao.ICartDao;
import com.sensor.entity.Cart;
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
import com.sensor.utils.transport.cart.CartTransportToController;
import com.sensor.utils.transport.cartProduct.CartProductTransportToController;
import com.sensor.utils.transport.product.ProductTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public CartInfoTransportToController getCartThatAreNotTerminadoByUserLoggedIn() {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        Cart cart = this.cartDao.getCartByUserThatAreNotTerminado(userLoggedIn).orElseThrow(() -> new GeneralException(HttpStatus.NOT_FOUND, "No se encontró un carrito para este usuario"));

        List<CartProductTransportToController> cartProductsTransport = new ArrayList<>();

        cart.getCartProducts().forEach(cp -> {
            String pathFile = cp.getProduct().getImage() != null ? DirectoryData.FILE_DIRECTORY_PRODUCT_IMAGES + cp.getProduct().getImage() : null;
            ProductTransportToController productTransportToController = new ProductTransportToController(cp.getProduct(), FileHelper.filePathToBase64String(pathFile,  DirectoryData.PRODUCT_DEFAULT_IMAGE));
            cartProductsTransport.add(new CartProductTransportToController(productTransportToController, cp.getQuantity(), cp.getCreated()));
        });

        CartTransportToController cartTransport = new CartTransportToController();
        cartTransport.setCart(cart);
        cartTransport.setProducts(cartProductsTransport);

        CartState state = cart.getState();
        CartStateStrategy strategy = cartStateStrategyFactory.getStrategy(state);

        return strategy.getCartInfo(userLoggedIn, cartTransport);
    }
}
