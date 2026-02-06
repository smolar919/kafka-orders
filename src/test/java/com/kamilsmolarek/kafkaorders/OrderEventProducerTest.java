package com.kamilsmolarek.kafkaorders;

import com.kamilsmolarek.kafkaorders.dto.OrderEventMessage;
import com.kamilsmolarek.kafkaorders.infrastructure.kafka.OrderEventProducer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OrderEventProducerTest {

    @MockitoBean
    private KafkaTemplate<String, OrderEventMessage> kafkaTemplate;

    @Autowired
    private OrderEventProducer orderEventProducer;

    @Test
    void shouldSendMessageToKafka() {
        OrderEventMessage message = OrderEventMessage.builder()
                .shipmentNumber("SH12345")
                .email("test@example.com")
                .countryFrom("USA")
                .countryTo("CAN")
                .statusCode(40)
                .build();

        orderEventProducer.sendOrderEvent(message);

        ArgumentCaptor<OrderEventMessage> captor = ArgumentCaptor.forClass(OrderEventMessage.class);

        Mockito.verify(kafkaTemplate).send(Mockito.eq("order-events"), Mockito.eq("SH12345"), captor.capture());

        assertThat(captor.getValue().getEmail()).isEqualTo("test@example.com");
        assertThat(captor.getValue().getStatusCode()).isEqualTo(40);
    }
}