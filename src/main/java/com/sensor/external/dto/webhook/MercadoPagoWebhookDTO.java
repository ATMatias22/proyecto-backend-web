package com.sensor.external.dto.webhook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MercadoPagoWebhookDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("live_mode")
    private boolean liveMode;

    @JsonProperty("type")
    private String type;

    @JsonProperty("data")
    private DataWebhookDTO data;

    @JsonProperty("date_created")
    private String dateCreated;


}