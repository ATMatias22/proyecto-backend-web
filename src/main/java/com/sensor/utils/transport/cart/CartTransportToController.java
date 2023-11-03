package com.sensor.utils.transport.cart;

import com.sensor.entity.Cart;
import com.sensor.utils.transport.cartProduct.CartProductTransportToController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartTransportToController {

    private Cart cart;

    private List<CartProductTransportToController> products;
}
