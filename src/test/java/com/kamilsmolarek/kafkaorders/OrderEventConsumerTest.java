package com.kamilsmolarek.kafkaorders;

import com.kamilsmolarek.kafkaorders.application.OrderService;
import com.kamilsmolarek.kafkaorders.dto.OrderEventForm;
import com.kamilsmolarek.kafkaorders.dto.OrderEventMessage;
import com.kamilsmolarek.kafkaorders.infrastructure.kafka.OrderEventConsumer;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderEventConsumerTest {

        @MockitoBean
        private OrderService orderServiceMock;

        @Autowired
        private OrderEventConsumer consumer;

        @Test
        void shouldForwardMessageToOrderService() {

            OrderEventMessage message = OrderEventMessage.builder()
                    .shipmentNumber("SHIP123")
                    .email("test@example.com")
                    .countryFrom("USA")
                    .countryTo("PL")
                    .statusCode(10)
                    .build();

            consumer.consumeOrderEvent(message);

            ArgumentCaptor<OrderEventForm> captor = ArgumentCaptor.forClass(OrderEventForm.class);
            verify(orderServiceMock, times(1)).saveOrderEventLog(captor.capture());

            assertThat(captor.getValue())
                    .hasFieldOrPropertyWithValue("email", "test@example.com")
                    .hasFieldOrPropertyWithValue("statusCode", 10)
                    .hasFieldOrPropertyWithValue("shipmentNumber", "SHIP123")
                    .hasFieldOrPropertyWithValue("countryFrom", "USA")
                    .hasFieldOrPropertyWithValue("countryTo", "PL");
        }
}
