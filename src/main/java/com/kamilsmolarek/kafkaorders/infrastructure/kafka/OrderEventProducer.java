package com.kamilsmolarek.kafkaorders.infrastructure.kafka;

import com.kamilsmolarek.kafkaorders.dto.OrderEventMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {

    private String topic;

    private final KafkaTemplate<String, OrderEventMessage> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderEventMessage> kafkaTemplate,
                              @Value("${kafka.topic.order-events}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEventMessage message) {
        kafkaTemplate.send(topic, message.getShipmentNumber(), message);
    }
}
