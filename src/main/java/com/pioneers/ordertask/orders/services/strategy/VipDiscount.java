package com.pioneers.ordertask.orders.services.strategy;

import com.pioneers.ordertask.orders.models.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VipDiscount implements Discount{

    @Override
    public BigDecimal discount(final Order order, final BigDecimal currentPrice) {

        if (order.isCustomerVip()) {
            return currentPrice.multiply(BigDecimal.valueOf(0.9));
        }

        return currentPrice;
    }

}
