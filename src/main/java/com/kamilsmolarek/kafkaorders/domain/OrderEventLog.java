package com.kamilsmolarek.kafkaorders.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_event_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEventLog {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "shipment_number", nullable = false)
    private String shipmentNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "country_from", nullable = false)
    private String countryFrom;

    @Column(name = "country_to", nullable = false)
    private String countryTo;

    @Column(name = "status_code", nullable = false)
    private Integer statusCode;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
