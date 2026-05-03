package com.pioneers.ordertask.customers.repositories;

import com.pioneers.ordertask.customers.models.Customer;
import com.pioneers.ordertask.database.Database;
import com.pioneers.ordertask.database.DatabaseImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CustomerRepo {

    private final Database<Customer, UUID> database = new DatabaseImpl<>(Customer::getId);

    public Customer save(final Customer customer) {
        if (existsById(customer.getId())) {
            log.info("Customer already exists");
            throw new IllegalArgumentException("Customer already exists");
        }

        if (existsByEmail(customer.getEmail())) {
            log.info("Customer with email {} already exists", customer.getEmail());
            throw new IllegalArgumentException("Customer with email " + customer.getEmail() + " already exists");
        }

        customer.setId(UUID.randomUUID());
        log.info("Customer with id {} saving request", customer);

        return database.save(customer);
    }

    public Customer update(final Customer customer){
        if (!existsById(customer.getId())) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        log.info("Customer updating request");
        return database.save(customer);
    }

    public Optional<Customer> findById(final UUID id) {
        if (!existsById(id)) {
            throw new IllegalArgumentException("Customer does not exist");
        }

        log.info("Finding customer by id request");
        return database.findById(id);
    }

    public Collection<Customer> findAll() {
        log.info("Finding all customers request");
        return database.findAll();
    }

    public void removeById(final UUID id) {
        if (!existsById(id)) {
            throw new IllegalArgumentException("Customer does not exist");
        }
        log.info("Customer deleting request");
        database.removeById(id);
    }

    public boolean existsById(final UUID id) {
        final boolean exist = database.exists(id);
        log.info("customer with id {} exists: {}", id, exist);
        return exist;
    }

    public boolean existsByEmail(final String email) {
        return findAll().stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }

}
