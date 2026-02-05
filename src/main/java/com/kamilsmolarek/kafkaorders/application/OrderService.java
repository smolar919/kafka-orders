package com.kamilsmolarek.kafkaorders.application;

import com.kamilsmolarek.kafkaorders.domain.OrderEventLog;
import com.kamilsmolarek.kafkaorders.dto.OrderEventForm;

public interface OrderService {
    OrderEventLog saveOrderEventLog(OrderEventForm orderEventForm);
}
