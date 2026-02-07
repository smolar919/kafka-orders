package com.kamilsmolarek.kafkaorders.infrastructure.kafka;

import com.kamilsmolarek.kafkaorders.application.OrderService;
import com.kamilsmolarek.kafkaorders.dto.OrderEventMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {
    private final OrderService orderService;

    public OrderEventConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(
            topics = "${kafka.topic.order-events}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeOrderEvent(OrderEventMessage message) {
        orderService.saveOrderEventLog(message.toOrderEventForm());
    }
}
