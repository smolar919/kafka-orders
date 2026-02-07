package com.kamilsmolarek.kafkaorders.api;

import com.kamilsmolarek.kafkaorders.dto.OrderEventForm;
import com.kamilsmolarek.kafkaorders.dto.OrderEventMessage;
import com.kamilsmolarek.kafkaorders.infrastructure.kafka.OrderEventProducer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderEventController {

    private final OrderEventProducer orderEventProducer;

    public OrderEventController(OrderEventProducer orderEventProducer) {
        this.orderEventProducer = orderEventProducer;
    }

    @PostMapping("/order-events")
    public ResponseEntity<Void> acceptOrderEvent(@Valid @RequestBody OrderEventForm orderEventForm) {
        OrderEventMessage orderEventMessage = OrderEventMessage.builder()
                .shipmentNumber(orderEventForm.getShipmentNumber())
                .email(orderEventForm.getEmail())
                .countryFrom(orderEventForm.getCountryFrom())
                .countryTo(orderEventForm.getCountryTo())
                .statusCode(orderEventForm.getStatusCode())
                .build();

        orderEventProducer.sendOrderEvent(orderEventMessage);

        return ResponseEntity.ok().build();
    }
}
