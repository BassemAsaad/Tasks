package com.pioneers.ordertask.products.services;

import com.pioneers.ordertask.products.dtos.ProductRequest;
import com.pioneers.ordertask.products.dtos.ProductResponse;
import com.pioneers.ordertask.products.dtos.ProductUpdate;
import com.pioneers.ordertask.products.models.Product;
import com.pioneers.ordertask.products.repositories.ProductRepo;
import com.pioneers.ordertask.products.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public ProductResponse create(final ProductRequest request) {
        log.info("Product creation request");
        final Product product = ProductMapper.fromRequestToProduct(request);

        final Product saved = productRepo.save(product);
        log.info("Product created with id: {}", saved.getId());

        return ProductMapper.fromProductToResponse(saved);
    }

    @Override
    public ProductResponse getById(final UUID id) {
        final Product product = getProductById(id);
        log.info("Product found");
        return ProductMapper.fromProductToResponse(product);
    }

    @Override
    public void deleteById(final UUID id) {
        final Product product = getProductById(id);
        productRepo.removeById(product.getId());
        log.info("Product deleted with id: {}", id);
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepo.findAll().stream()
                .map(ProductMapper::fromProductToResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> getLowStockProducts(final int threshold) {
        return productRepo.findAll().stream()
                .filter(product -> product.isProductLowStock(threshold))
                .map(ProductMapper::fromProductToResponse)
                .toList();
    }

    @Override
    public ProductResponse update(final UUID id, final ProductUpdate request) {
        final Product product = getProductById(id);

        if (request.name() != null) {
            product.setName(request.name());
        }
        if (request.price() != null) {
            product.setPrice(request.price());
        }
        if (request.stockQuantity() != null) {
            product.setStockQuantity(request.stockQuantity());
        }

        final Product updated = productRepo.update(product);
        log.info("Product updated with id: {}", updated.getId());

        return ProductMapper.fromProductToResponse(updated);
    }

    private Product getProductById(UUID id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product does not exist"));
    }
}
