package com.pioneers.discount.controllers;


import com.pioneers.discount.models.dtos.requests.OrderReceipt;
import com.pioneers.discount.services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("discount")
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping("apply")
    public BigDecimal applyDiscountApi(@RequestBody OrderReceipt orderReceipt) {
        return discountService.applyDiscount(orderReceipt);
    }

}
