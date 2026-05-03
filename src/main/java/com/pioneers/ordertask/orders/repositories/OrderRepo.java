package com.pioneers.ordertask.orders.repositories;

import com.pioneers.ordertask.database.Database;
import com.pioneers.ordertask.database.DatabaseImpl;
import com.pioneers.ordertask.orders.models.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class OrderRepo {

    private final Database<Order, UUID> database = new DatabaseImpl<>(Order::getId);

    public Order save(final Order order) {
        order.setId(UUID.randomUUID());
        log.info("Saving order with id: {}", order.getId());
        return database.save(order);
    }

    public Optional<Order> findById(final UUID id) {
        log.info("Finding order with id: {}", id);
        return database.findById(id);
    }

    public Collection<Order> findAll() {
        log.info("Getting all orders");
        return database.findAll();
    }

    public void removeById(final UUID id){
        log.info("Removing order with id: {}", id);
        database.removeById(id);
    }

    public boolean existsById(final UUID id) {
        final boolean exists = database.exists(id);
        log.info("Order with id {} exists: {}", id, exists);
        return exists;
    }

    public Order update(final Order order) {
        if (!existsById(order.getId())) {
            throw new IllegalArgumentException("Order does not exist");
        }
        log.info("Updating order with id: {}", order.getId());
        return database.save(order);
    }
}
