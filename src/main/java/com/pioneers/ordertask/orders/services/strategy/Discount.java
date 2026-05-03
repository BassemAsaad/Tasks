package com.pioneers.ordertask.orders.services.strategy;

import com.pioneers.ordertask.orders.models.Order;
import java.math.BigDecimal;

public interface Discount {

    BigDecimal discount(Order order, BigDecimal currentPrice);
}
