package com.uerles.marketplace_api.domain.dtos;

import com.uerles.marketplace_api.domain.Order;
import com.uerles.marketplace_api.domain.OrderItem;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDateTime moment,
        @NotNull(message = "O nome do cliente e obrigatorio")
        String customerName,
        @NotEmpty(message = "O pedido deve ter pelo menos um item")
        List<OrderItemDTO> items,
        Double total
) {
    public static OrderDTO fromEntity(Order order){
        List<OrderItemDTO> itemDTOS = order.getItems().stream()
                .map(OrderItemDTO::fromEntity)
                .toList();

        double totalValeu = itemDTOS.stream()
                .mapToDouble(item -> item.subTotal().doubleValue())
                .sum();
        return new OrderDTO(
                order.getId(),
                order.getMoment(),
                order.getCustomerName(),
                itemDTOS,
                totalValeu
        );
    }
}
