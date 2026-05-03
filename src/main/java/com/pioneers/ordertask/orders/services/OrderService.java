package com.pioneers.ordertask.orders.services;

import com.pioneers.ordertask.orders.dtos.OrderRequest;
import com.pioneers.ordertask.orders.dtos.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse create(OrderRequest request);

    OrderResponse getById(UUID id);

    List<OrderResponse> getAll();

}
