package com.sensor.controller;


import com.sensor.dto.cart.request.CartInfoRequest;
import com.sensor.dto.cart.response.CartInfoResponse;
import com.sensor.dto.cartProduct.request.AddProductInCartRequest;
import com.sensor.dto.cartProduct.request.RemoveProductInCartRequest;
import com.sensor.dto.cart.response.AddProductInCartResponse;
import com.sensor.dto.cart.response.RemoveProductInCartResponse;
import com.sensor.entity.CartProduct;
import com.sensor.mapper.CartMapper;
import com.sensor.service.ICartService;
import com.sensor.utils.transport.cart.CartInfoTransportToController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private ICartService cartService;
    @Autowired
    private CartMapper cartMapper;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CartInfoResponse> getCartThatAreNotTerminadoByUserLoggedIn() {
        return new ResponseEntity<>(this.cartMapper.cartTransportToControllerToCartInfoResponse(this.cartService.getCartThatAreNotTerminadoByUserLoggedIn()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "next-step")
    public ResponseEntity<CartInfoResponse> nextStep(@RequestBody CartInfoRequest cartInfoRequest) {
        CartInfoTransportToController cartInfoTransportToController = this.cartService.changeState(this.cartMapper.cartInfoRequestToCartInfoTransportToService(cartInfoRequest));

        CartInfoResponse cartInfoResponse = this.cartMapper.cartTransportToControllerToCartInfoResponse(cartInfoTransportToController);
        return new ResponseEntity<>(cartInfoResponse, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/products/{productId}")
    public ResponseEntity<AddProductInCartResponse> addProduct(@PathVariable("productId") Long productId, @RequestBody @Valid AddProductInCartRequest addProductInCartRequest) {
        CartProduct cartProduct = this.cartService.addProduct(productId,addProductInCartRequest.getQuantity());
        AddProductInCartResponse addProductInCartResponse = this.cartMapper.cartProductToAddProductInCartResponse(cartProduct);
        return new ResponseEntity<>(addProductInCartResponse, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/products/{productId}")
    public ResponseEntity<RemoveProductInCartResponse> removeProduct(@PathVariable("productId") Long productId, @RequestBody @Valid RemoveProductInCartRequest removeProductInCartRequest) {
        CartProduct cartProduct = this.cartService.removeProduct(productId,removeProductInCartRequest.getQuantity());
        RemoveProductInCartResponse removeProductInCartResponse = this.cartMapper.cartProductToRemoveProductInCartResponse(cartProduct);
        return new ResponseEntity<>(removeProductInCartResponse, HttpStatus.OK);
    }

}
