package com.pioneers.ordertask.products.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private UUID id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;

    public boolean isProductLowStock(final int threshold){
        return stockQuantity < threshold;
    }

    public void reduceStock(final int quantity){
        stockQuantity -= quantity;
    }
}
