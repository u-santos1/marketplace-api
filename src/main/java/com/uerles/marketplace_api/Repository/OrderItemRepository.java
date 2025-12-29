package com.uerles.marketplace_api.Repository;

import com.uerles.marketplace_api.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
