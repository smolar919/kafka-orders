package com.kamilsmolarek.kafkaorders.service;

import com.kamilsmolarek.kafkaorders.domain.OrderEventLog;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendOrderConfirmationEmail(OrderEventLog orderEventLog) {
        // Simulate sending an email
        System.out.println("Sending order confirmation email for order " + orderEventLog.getId() + " to " + orderEventLog.getEmail());
    }
}
