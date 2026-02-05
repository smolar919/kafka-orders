package com.kamilsmolarek.kafkaorders;

import com.kamilsmolarek.kafkaorders.application.OrderService;
import com.kamilsmolarek.kafkaorders.domain.OrderEventLog;
import com.kamilsmolarek.kafkaorders.dto.OrderEventForm;
import com.kamilsmolarek.kafkaorders.infrastructure.OrderEventLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderEventLogRepository orderEventLogRepository;

    @Test
    void testSaveOrderEventLog() {
        OrderEventForm orderEventForm = OrderEventForm.builder()
                .shipmentNumber("SH12345")
                .email("test@example.com")
                .countryFrom("USA")
                .countryTo("CAN")
                .statusCode(40)
                .build();

        OrderEventLog savedLog = orderService.saveOrderEventLog(orderEventForm);

        Optional<OrderEventLog> retrievedLog = orderEventLogRepository.findById(savedLog.getId());

        assertTrue(retrievedLog.isPresent(), "OrderEventLog should be present in the db");
        assertEquals("SH12345", retrievedLog.get().getShipmentNumber());
        assertEquals("test@example.com", retrievedLog.get().getEmail());
        assertEquals(40, retrievedLog.get().getStatusCode());
        assertNotNull(retrievedLog.get().getId(), "ID should not be null");
        assertNotNull(retrievedLog.get().getCreatedAt(), "CreatedAt should not be null");
    }
}
