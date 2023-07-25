package com.sensor.controller;


import com.sensor.dto.cart.response.CartInfoResponse;
import com.sensor.mapper.CartMapper;
import com.sensor.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
