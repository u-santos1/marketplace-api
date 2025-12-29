package com.uerles.marketplace_api.domain.dtos;

import com.uerles.marketplace_api.domain.OrderItem;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal initPrice,
        BigDecimal subTotal
) {
    public static OrderItemDTO fromEntity(OrderItem item){
        return new OrderItemDTO(
                item.getProduct().getId(),
                item.getProduct().getTitle(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()))
        );
    }
}
