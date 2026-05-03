package com.pioneers.ordertask.products.dtos;

import java.math.BigDecimal;

public record ProductUpdate(
        String name,
        BigDecimal price,
        Integer stockQuantity
) {
}
