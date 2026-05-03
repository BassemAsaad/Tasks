package com.pioneers.ordertask.orders.controllers;

import com.pioneers.ordertask.orders.dtos.OrderRequest;
import com.pioneers.ordertask.orders.dtos.OrderResponse;
import com.pioneers.ordertask.orders.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }
}
