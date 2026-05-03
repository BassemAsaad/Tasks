package com.pioneers.ordertask.products.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponse(
        UUID id,
        String name,
        BigDecimal price,
        int stockQuantity
) {
}
