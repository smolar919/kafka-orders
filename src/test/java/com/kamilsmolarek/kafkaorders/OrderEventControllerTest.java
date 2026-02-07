package com.kamilsmolarek.kafkaorders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamilsmolarek.kafkaorders.api.OrderEventController;
import com.kamilsmolarek.kafkaorders.dto.OrderEventForm;
import com.kamilsmolarek.kafkaorders.infrastructure.kafka.OrderEventProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderEventController.class)
public class OrderEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderEventProducer orderEventProducerMock;

    @Test
    void shouldAcceptOrderEventAndSendToKafka() throws Exception {

        String json = """
                {
                    "shipmentNumber": "SHIP123",
                    "email": "test@example.com",
                    "countryFrom": "USA",
                    "countryTo": "PL",
                    "statusCode": 10
                }
                """;

        mockMvc.perform(
                post("/api/order-events")
                    .contentType("application/json")
                    .content(json)
            )
            .andExpect(status().isOk());
    }
}
