package com.pioneers.ordertask.orders.models;

import com.pioneers.ordertask.products.models.Product;

public record Item(
        Product product,
        int orderedQuantity
) {
}
