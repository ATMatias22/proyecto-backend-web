package com.sensor.security.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserResponse {

    private Long id;

    private String name;

    private String lastname;

    private String email;

    private String country;

    private String datesBirth;

    private String created;

    private String updated;
}
