package com.pioneers.ordertask.products.services;

import com.pioneers.ordertask.products.dtos.ProductRequest;
import com.pioneers.ordertask.products.dtos.ProductResponse;
import com.pioneers.ordertask.products.dtos.ProductUpdate;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse create(ProductRequest request);

    ProductResponse getById(UUID id);

    ProductResponse update(UUID id, ProductUpdate request);

    void deleteById(UUID id);

    List<ProductResponse> getAll();

    List<ProductResponse> getLowStockProducts(int threshold);

}
