package com.pioneers.ordertask.orders.dtos;

import com.pioneers.ordertask.customers.models.CustomerType;
import com.pioneers.ordertask.orders.models.Item;
import com.pioneers.ordertask.orders.models.PaymentMethod;
import com.pioneers.ordertask.products.models.Product;

import java.util.List;

public record OrderRequest(
        String customerName,
        CustomerType customerType,
        PaymentMethod paymentMethod,
        List<Item> items
) {
}
