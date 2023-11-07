package com.sensor.external.web_admin.dto.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddDeviceFromWebAdminRequest {


    @Size(max = 90, message = "El codigo de dispositivo no debe ser mayor a 90")
    @NotBlank(message = "El codigo de dispositivo no puede ser nulo ni vacio")
    private String deviceCode;

    @Size(max = 75, message = "La contraseña del dispositivo no debe ser mayor a 75")
    @NotBlank(message = "La contraseña del dispositivo no puede ser nulo ni vacio")
    private String password;
}
