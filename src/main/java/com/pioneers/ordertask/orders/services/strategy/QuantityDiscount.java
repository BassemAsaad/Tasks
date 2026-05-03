package com.pioneers.ordertask.orders.services.strategy;

import com.pioneers.ordertask.orders.models.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class QuantityDiscount implements Discount {

    private static final int PRODUCT_QUANTITY_FOR_DISCOUNT = 10;

    @Override
    public BigDecimal discount(final Order order, final BigDecimal currentPrice) {

        if (order.getAllProductsQuantity() > PRODUCT_QUANTITY_FOR_DISCOUNT){
            return currentPrice.multiply(BigDecimal.valueOf(0.95));
        }

        return currentPrice;
    }

}
