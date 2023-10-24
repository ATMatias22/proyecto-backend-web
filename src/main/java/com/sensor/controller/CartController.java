package com.sensor.controller;


import com.sensor.dto.cart.request.CartInfoRequest;
import com.sensor.dto.cart.response.cartforuser.CartInfoForUserResponse;
import com.sensor.dto.cartProduct.request.AddProductInCartRequest;
import com.sensor.dto.cartProduct.request.RemoveProductInCartRequest;
import com.sensor.dto.cart.response.AddProductInCartResponse;
import com.sensor.dto.cart.response.RemoveProductInCartResponse;
import com.sensor.entity.CartProduct;
import com.sensor.external.dto.preference.PreferenceDTO;
import com.sensor.external.dto.webhook.MercadoPagoWebhookDTO;
import com.sensor.mapper.CartMapper;
import com.sensor.security.MainUser;
import com.sensor.security.entity.User;
import com.sensor.security.service.IUserService;
import com.sensor.service.ICartService;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;
    @Autowired
    private CartMapper cartMapper;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartInfoForUserResponse> getCartByUserLoggedIn() {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        return new ResponseEntity<>(this.cartMapper.cartTransportToControllerToCartInfoResponse(this.cartService.getCartByUserLoggedIn(userLoggedIn)), HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "next-step")
    public ResponseEntity<CartInfoForUserResponse> nextStep(@RequestBody CartInfoRequest cartInfoRequest) {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        CartInfoTransportToController cartInfoTransportToController = this.cartService.changeState(this.cartMapper.cartInfoRequestToCartInfoTransportToService(cartInfoRequest),userLoggedIn );

        CartInfoForUserResponse cartInfoForUserResponse = this.cartMapper.cartTransportToControllerToCartInfoResponse(cartInfoTransportToController);
        return new ResponseEntity<>(cartInfoForUserResponse, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "preference")
    public ResponseEntity<PreferenceDTO> getPreference() {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());

        String preferenceId = this.cartService.getPreferenceId(userLoggedIn);
        PreferenceDTO preferenceDTO = new PreferenceDTO(preferenceId);

        return new ResponseEntity<>(preferenceDTO, HttpStatus.OK);
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "preference-notification")
    public ResponseEntity<Void> preferenceNotification(MercadoPagoWebhookDTO mercadoPagoWebhookDTO) {
        this.cartService.preferenceNotification(mercadoPagoWebhookDTO );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/products/{productId}")
    public ResponseEntity<AddProductInCartResponse> addProduct(@PathVariable("productId") Long productId, @RequestBody @Valid AddProductInCartRequest addProductInCartRequest) {

        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());
        CartProduct cartProduct = this.cartService.addProduct(productId,addProductInCartRequest.getQuantity(), userLoggedIn );
        AddProductInCartResponse addProductInCartResponse = this.cartMapper.cartProductToAddProductInCartResponse(cartProduct);
        return new ResponseEntity<>(addProductInCartResponse, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/products/{productId}")
    public ResponseEntity<RemoveProductInCartResponse> removeProduct(@PathVariable("productId") Long productId, @RequestBody @Valid RemoveProductInCartRequest removeProductInCartRequest) {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());
        CartProduct cartProduct = this.cartService.removeProduct(productId,removeProductInCartRequest.getQuantity(), userLoggedIn);
        RemoveProductInCartResponse removeProductInCartResponse = this.cartMapper.cartProductToRemoveProductInCartResponse(cartProduct);
        return new ResponseEntity<>(removeProductInCartResponse, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/cancel")
    public ResponseEntity<Void> cancelCart() {
        MainUser mu = (MainUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userLoggedIn = this.userService.getUserByEmail(mu.getUsername());
        this.cartService.cancelCart(userLoggedIn);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
