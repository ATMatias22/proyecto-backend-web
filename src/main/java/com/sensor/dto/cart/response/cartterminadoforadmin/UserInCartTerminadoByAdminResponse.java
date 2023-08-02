package com.sensor.dto.cart.response.cartterminadoforadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInCartTerminadoByAdminResponse {

    private Long userId;

    private String name;

    private String lastname;

    private String email;
}
