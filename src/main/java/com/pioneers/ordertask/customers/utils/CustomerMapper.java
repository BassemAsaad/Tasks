package com.pioneers.ordertask.customers.utils;

import com.pioneers.ordertask.customers.dtos.CustomerRequest;
import com.pioneers.ordertask.customers.dtos.CustomerResponse;
import com.pioneers.ordertask.customers.models.Customer;

public final class CustomerMapper {

    private CustomerMapper() {
        throw new AssertionError("You cannot instantiate this class");
    }

    public static Customer fromRequestToCustomer(final CustomerRequest request) {
        return Customer.builder()
                .username(request.username())
                .email(request.email())
                .customerType(request.customerType())
                .password(request.password())
                .build();
    }

    public static CustomerResponse fromCustomerToResponse(final Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getCustomerType()
        );
    }

}
