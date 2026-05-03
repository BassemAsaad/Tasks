package com.pioneers.ordertask.customers.services;

import com.pioneers.ordertask.customers.dtos.CustomerRequest;
import com.pioneers.ordertask.customers.dtos.CustomerResponse;
import com.pioneers.ordertask.customers.dtos.CustomerUpdate;
import com.pioneers.ordertask.customers.models.Customer;
import com.pioneers.ordertask.customers.repositories.CustomerRepo;
import com.pioneers.ordertask.customers.utils.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements  CustomerService {
    private final CustomerRepo customerRepo;

    @Override
    public CustomerResponse create(final CustomerRequest request) {
        log.info("Customer creation request");
        final Customer customer = CustomerMapper.fromRequestToCustomer(request);

        final Customer saved = customerRepo.save(customer);
        log.info("Customer created with id: {}", saved.getId());

        return CustomerMapper.fromCustomerToResponse(saved);
    }

    @Override
    public CustomerResponse getById(final UUID id) {
        final Customer customer = getCustomerById(id);
        log.info("Customer found");
        return CustomerMapper.fromCustomerToResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        log.info("Getting all customers");
        return customerRepo.findAll().stream()
                .map(CustomerMapper::fromCustomerToResponse)
                .toList();
    }

    @Override
    public void delete(final UUID id) {
        final Customer customer = getCustomerById(id);
        customerRepo.removeById(customer.getId());
        log.info("Customer deleted with id: {}", id);
    }

    @Override
    public CustomerResponse update(final UUID id, final CustomerUpdate request) {
        final Customer customer = getCustomerById(id);

        if (request.email() != null) {
            customer.setEmail(request.email());
        }
        if (request.username() != null) {
            customer.setUsername(request.username());
        }
        if (request.password() != null) {
            customer.setPassword(request.password());
        }
        if (request.customerType() != null) {
            customer.setCustomerType(request.customerType());
        }

        return CustomerMapper.fromCustomerToResponse(customerRepo.save(customer));
    }

    private @NonNull Customer getCustomerById(UUID id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));
    }
}
