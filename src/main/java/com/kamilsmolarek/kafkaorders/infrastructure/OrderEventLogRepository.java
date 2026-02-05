package com.kamilsmolarek.kafkaorders.infrastructure;

import com.kamilsmolarek.kafkaorders.domain.OrderEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventLogRepository extends JpaRepository<OrderEventLog, String> {
}
