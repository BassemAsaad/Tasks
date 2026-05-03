package com.pioneers.ordertask.customers.dtos;

import com.pioneers.ordertask.customers.models.CustomerType;
public record CustomerRequest(
        String username,
        String email,
        CustomerType customerType,
        String password
) {
}
