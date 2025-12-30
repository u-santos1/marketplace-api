package com.uerles.marketplace_api.Controller;

import com.uerles.marketplace_api.config.Service.OrderService;
import com.uerles.marketplace_api.domain.Order;
import com.uerles.marketplace_api.domain.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;
    public OrderController(OrderService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderDTO data
            ,UriComponentsBuilder  uriComponentsBuilder) throws IllegalAccessException {
        OrderDTO newOrder = service.create(data);
        URI uri = uriComponentsBuilder.path("/api/orders/{id}").buildAndExpand(newOrder.id()).toUri();
        return ResponseEntity.created(uri).body(newOrder);

    }
}
