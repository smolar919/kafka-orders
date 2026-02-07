package com.kamilsmolarek.kafkaorders.service;

import com.kamilsmolarek.kafkaorders.domain.OrderEventLog;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendOrderConfirmationEmail(OrderEventLog orderEventLog) {

        String emailContent = String.format(
                "To: %s\n" +
                "Shipment Number: %s\n" +
                "From: %s\n" +
                "To: %s\n" +
                "Status Code: %s\n",
                orderEventLog.getEmail(),
                orderEventLog.getShipmentNumber(),
                orderEventLog.getCountryFrom(),
                orderEventLog.getCountryTo(),
                orderEventLog.getStatusCode()
        );

        System.out.println("Sending order details:\n" + emailContent);

    }
}
