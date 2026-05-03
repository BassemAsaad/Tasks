package com.pioneers.ordertask.orders.models;

import com.pioneers.ordertask.customers.models.CustomerType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Order {

    private UUID id;
    private String customerName;
    private CustomerType customerType;
    private PaymentMethod paymentMethod;

    @Builder.Default
    private List<Item> items = new ArrayList<>();

    @Builder.Default
    private BigDecimal priceAfterDiscount = BigDecimal.valueOf(0);

    public Long getAllProductsQuantity() {
        return items.stream()
                .mapToLong(Item::orderedQuantity)
                .sum();
    }

    public boolean isCustomerVip() {
        return customerType == CustomerType.VIP;
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(item -> item.product()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(item.orderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalDiscount() {
        return getTotalPrice().subtract(priceAfterDiscount);
    }

}
