package com.pioneers.ordertask.customers.controllers;

import com.pioneers.ordertask.customers.dtos.CustomerRequest;
import com.pioneers.ordertask.customers.dtos.CustomerResponse;
import com.pioneers.ordertask.customers.dtos.CustomerUpdate;
import com.pioneers.ordertask.customers.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable UUID id, @RequestBody CustomerUpdate request) {
        return ResponseEntity.ok(customerService.update(id, request));
    }

}
