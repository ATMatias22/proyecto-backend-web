package com.sensor.security.dto.user.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConfirmRegisterUserRequest {
    private String token;
}
