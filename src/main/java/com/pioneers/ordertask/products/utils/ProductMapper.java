package com.pioneers.ordertask.products.utils;

import com.pioneers.ordertask.products.dtos.ProductRequest;
import com.pioneers.ordertask.products.dtos.ProductResponse;
import com.pioneers.ordertask.products.models.Product;

public final class ProductMapper {

    private ProductMapper() {
        throw new AssertionError("You cannot instantiate this class");
    }

    public static Product fromRequestToProduct(final ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .build();
    }

    public static ProductResponse fromProductToResponse(final Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

}
