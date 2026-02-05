package com.kamilsmolarek.kafkaorders.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderEventForm {

    @NotBlank
    private String shipmentNumber;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String countryFrom;

    @NotBlank
    private String countryTo;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer statusCode;
}
