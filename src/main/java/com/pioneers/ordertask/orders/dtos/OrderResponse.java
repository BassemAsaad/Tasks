package com.pioneers.ordertask.orders.dtos;

import com.pioneers.ordertask.customers.models.CustomerType;
import com.pioneers.ordertask.orders.models.Item;
import com.pioneers.ordertask.orders.models.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponse(
        UUID id,
        String customerName,
        CustomerType customerType,
        PaymentMethod paymentMethod,
        List<Item> items,
        BigDecimal totalPrice,
        BigDecimal priceDiscounted,
        BigDecimal priceAfterDiscount
) {
}
