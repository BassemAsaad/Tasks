package com.pioneers.ordertask.products.repositories;

import com.pioneers.ordertask.database.Database;
import com.pioneers.ordertask.database.DatabaseImpl;
import com.pioneers.ordertask.products.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepo {
    private final Database<Product, UUID> database = new DatabaseImpl<>(Product::getId);

    public Product save(final Product product) {
        if (existsById(product.getId())) {
            throw new IllegalArgumentException("Product already exists");
        }
        if (existsByName(product.getName())) {
            throw new IllegalArgumentException("Product already exists");
        }

        product.setId(UUID.randomUUID());
        log.info("Saving product with id: {}", product.getId());
        return database.save(product);
    }

    public Optional<Product> findById(final UUID id) {
        log.info("Finding product with id: {}", id);
        return database.findById(id);
    }

    public Optional<Product> findByName(final String name) {
        return findAll().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

    public Collection<Product> findAll() {
        return database.findAll();
    }

    public void removeById(final UUID id) {
        if (!existsById(id)) {
            throw new IllegalArgumentException("Product does not exist");
        }
        log.info("Removing product with id: {}", id);
        database.removeById(id);
    }

    public Product update(final Product product) {
        if (!existsById(product.getId())) {
            throw new IllegalArgumentException("Product does not exist");
        }
        log.info("Updating product with id: {}", product.getId());
        return database.save(product);
    }

    public boolean existsById(final UUID id) {
        final boolean exists = database.exists(id);
        log.info("Product with id {} exists: {}", id, exists);
        return exists;
    }

    public boolean existsByName(final String name) {
        return findAll().stream()
                .anyMatch(product -> product.getName().equals(name));
    }

}
