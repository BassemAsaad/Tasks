package com.pioneers.ordertask.products.controllers;

import com.pioneers.ordertask.products.dtos.ProductRequest;
import com.pioneers.ordertask.products.dtos.ProductResponse;
import com.pioneers.ordertask.products.dtos.ProductUpdate;
import com.pioneers.ordertask.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable UUID id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable UUID id, @RequestBody ProductUpdate request) {
        return ResponseEntity.ok(productService.update(id, request));
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponse>> getLowStockProducts(@RequestParam int threshold) {
        return ResponseEntity.ok(productService.getLowStockProducts(threshold));
    }

}
