package com.pioneers.ordertask.customers.dtos;

import com.pioneers.ordertask.customers.models.CustomerType;

import java.util.UUID;

public record CustomerUpdate(
        String username,
        String email,
        String password,
        CustomerType customerType
) {
}
