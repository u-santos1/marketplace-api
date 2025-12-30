package com.uerles.marketplace_api.config.Service;

import com.uerles.marketplace_api.Repository.OrderItemRepository;
import com.uerles.marketplace_api.Repository.OrderRepository;
import com.uerles.marketplace_api.Repository.ProductRepository;
import com.uerles.marketplace_api.domain.Order;
import com.uerles.marketplace_api.domain.OrderItem;
import com.uerles.marketplace_api.domain.Product;
import com.uerles.marketplace_api.domain.dtos.OrderDTO;
import com.uerles.marketplace_api.domain.dtos.OrderItemDTO;
import com.uerles.marketplace_api.domain.dtos.ProductDTO;
import com.uerles.marketplace_api.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }
    @Transactional
    public OrderDTO create(OrderDTO data) throws IllegalAccessException {
        Order order = new Order();
        order.setMoment(LocalDateTime.now());
        order.setCustomerName(data.customerName());
        orderRepository.save(order);
        List<OrderItem> item = new ArrayList<>();

        for (OrderItemDTO itemDTO : data.items()){
            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

            if(product.getQuantityStock() < itemDTO.quantity()){
                throw new IllegalAccessException("Estoque insuficiente para o produto: " + product.getTitle());
            }
            product.setQuantityStock(product.getQuantityStock() - itemDTO.quantity());
            productRepository.save(product);

            OrderItem item1 = new OrderItem();
            item1.setOrder(order);
            item1.setProduct(product);
            item1.setQuantity(itemDTO.quantity());

            item1.setUnitPrice(product.getPrice());
            orderItemRepository.save(item1);
            item.add(item1);

        }
        order.setItems(item);
        return OrderDTO.fromEntity(order);
    }
}
