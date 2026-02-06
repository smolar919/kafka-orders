package com.kamilsmolarek.kafkaorders.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEventMessage {
    private String shipmentNumber;
    private String email;
    private String countryFrom;
    private String countryTo;
    private Integer statusCode;

    public OrderEventForm toOrderEventForm() {
        return OrderEventForm.builder()
                .shipmentNumber(this.shipmentNumber)
                .email(this.email)
                .countryFrom(this.countryFrom)
                .countryTo(this.countryTo)
                .statusCode(this.statusCode)
                .build();
    }
}

