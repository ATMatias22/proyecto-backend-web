package com.sensor.external.dto;

import javax.validation.constraints.NotNull;

public class PayerIdentificationDTO {

    private String type;

    @NotNull
    private String number;

    public PayerIdentificationDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
