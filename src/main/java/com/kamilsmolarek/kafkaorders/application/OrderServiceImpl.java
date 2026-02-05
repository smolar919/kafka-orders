package com.kamilsmolarek.kafkaorders.application;

import com.kamilsmolarek.kafkaorders.domain.OrderEventLog;
import com.kamilsmolarek.kafkaorders.dto.OrderEventForm;
import com.kamilsmolarek.kafkaorders.infrastructure.OrderEventLogRepository;
import com.kamilsmolarek.kafkaorders.service.EmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderEventLogRepository orderEventLogRepository;
    private final EmailService emailService;

    public OrderServiceImpl(OrderEventLogRepository orderEventLogRepository, EmailService emailService) {
        this.orderEventLogRepository = orderEventLogRepository;
        this.emailService = emailService;
    }

    @Override
    public OrderEventLog saveOrderEventLog(OrderEventForm orderEventForm) {

        OrderEventLog orderEventLog = OrderEventLog.builder()
                .id(UUID.randomUUID().toString())
                .shipmentNumber(orderEventForm.getShipmentNumber())
                .email(orderEventForm.getEmail())
                .countryFrom(orderEventForm.getCountryFrom())
                .countryTo(orderEventForm.getCountryTo())
                .statusCode(orderEventForm.getStatusCode())
                .createdAt(LocalDateTime.now())
                .build();

        OrderEventLog savedOrder = orderEventLogRepository.save(orderEventLog);

        emailService.sendOrderConfirmationEmail(savedOrder);

        return savedOrder;
    }
}
