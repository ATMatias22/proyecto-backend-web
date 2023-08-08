package com.sensor.controller;


import com.sensor.dto.cart.request.CartInfoRequest;
import com.sensor.dto.cart.response.cartforuser.CartInfoForUserResponse;
import com.sensor.dto.cart.response.cartentregaforuser.CartEntregaForUserLoggedInResponse;
import com.sensor.dto.cart.response.cartterminadoforadmin.FinishedCartTerminadoForAdminResponse;
import com.sensor.dto.cart.response.cartterminadoforuser.CartTerminadoForUserLoggedInResponse;
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
import java.util.List;

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
    public ResponseEntity<CartInfoForUserResponse> getCartThatAreNotTerminadoByUserLoggedIn() {
        return new ResponseEntity<>(this.cartMapper.cartTransportToControllerToCartInfoResponse(this.cartService.getCartThatAreNotTerminadoOrEntregaByUserLoggedIn()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/all-terminado")
    public ResponseEntity<List<CartTerminadoForUserLoggedInResponse>> getAllCartsWhereTheStatusIsTerminadoByUserLoggedIn() {
        return new ResponseEntity<>(this.cartMapper.cartToCartTerminadoForUserLoggedInResponse(this.cartService.getAllCartsWhereTheStatusIsTerminadoByUserLoggedIn()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/all-entrega")
    public ResponseEntity<List<CartEntregaForUserLoggedInResponse>> getAllCartsWhereTheStatusIsEntregaByUserLoggedIn() {
        return new ResponseEntity<>(this.cartMapper.cartToCartEntregaForUserLoggedInResponse(this.cartService.getAllCartsWhereTheStatusIsEntregaByUserLoggedIn()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, value = "/all")
    public ResponseEntity<List<FinishedCartTerminadoForAdminResponse>> getAllCartsWhereTheStatusIsTerminado() {
        return new ResponseEntity<>(this.cartMapper.cartToFinishedCartTerminadoForAdminResponse(this.cartService.getAllCartsWhereTheStatusIsTerminadoByUserLoggedIn()), HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE}, value = "next-step")
    public ResponseEntity<CartInfoForUserResponse> nextStep(@RequestBody CartInfoRequest cartInfoRequest) {
        CartInfoTransportToController cartInfoTransportToController = this.cartService.changeState(this.cartMapper.cartInfoRequestToCartInfoTransportToService(cartInfoRequest));

        CartInfoForUserResponse cartInfoForUserResponse = this.cartMapper.cartTransportToControllerToCartInfoResponse(cartInfoTransportToController);
        return new ResponseEntity<>(cartInfoForUserResponse, HttpStatus.OK);
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