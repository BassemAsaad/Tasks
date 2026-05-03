package com.pioneers.ordertask.customers.services;

import com.pioneers.ordertask.customers.dtos.CustomerRequest;
import com.pioneers.ordertask.customers.dtos.CustomerResponse;
import com.pioneers.ordertask.customers.dtos.CustomerUpdate;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse getById(UUID id);

    List<CustomerResponse> getAll();

    void delete(UUID id);

    CustomerResponse update(UUID id, CustomerUpdate request);

}
