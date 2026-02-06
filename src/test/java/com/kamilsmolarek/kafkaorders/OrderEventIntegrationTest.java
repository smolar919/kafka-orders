package com.kamilsmolarek.kafkaorders;

import com.kamilsmolarek.kafkaorders.application.OrderService;
import com.kamilsmolarek.kafkaorders.dto.OrderEventMessage;
import com.kamilsmolarek.kafkaorders.infrastructure.kafka.OrderEventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "order-events" })
public class OrderEventIntegrationTest {

    @Autowired
    private OrderEventProducer orderEventProducer;

    @MockitoBean
    private OrderService orderService;

    @Test
    void testMessageFlow() throws InterruptedException {
        OrderEventMessage message = OrderEventMessage.builder()
                .shipmentNumber("SHIP999")
                .email("test@example.com")
                .countryFrom("USA")
                .countryTo("PL")
                .statusCode(20)
                .build();

        orderEventProducer.sendOrderEvent(message);

        Thread.sleep(1000);

        verify(orderService, times(1)).saveOrderEventLog(any());
    }

}
