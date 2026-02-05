package com.kamilsmolarek.kafkaorders.application;

import com.kamilsmolarek.kafkaorders.domain.OrderEventLog;
import com.kamilsmolarek.kafkaorders.dto.OrderEventForm;
import com.kamilsmolarek.kafkaorders.infrastructure.OrderEventLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderEventLogRepository orderEventLogRepository;

    public OrderServiceImpl(OrderEventLogRepository orderEventLogRepository) {
        this.orderEventLogRepository = orderEventLogRepository;
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

        return orderEventLogRepository.save(orderEventLog);
    }
}
