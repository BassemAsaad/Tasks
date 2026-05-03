package com.pioneers.ordertask.customers.dtos;

import com.pioneers.ordertask.customers.models.CustomerType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CustomerResponse(
        UUID id,
        String username,
        String email,
        CustomerType customerType
) {
}
