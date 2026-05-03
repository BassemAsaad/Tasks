package com.pioneers.ordertask.customers.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Data
public class Customer {

    private UUID id;
    private String username;
    private String email;
    private CustomerType customerType;
    private String password;

}
