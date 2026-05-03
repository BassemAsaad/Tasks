package com.pioneers.ordertask.orders.services.strategy;

import com.pioneers.ordertask.orders.models.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DiscountProcessor {

    private final List<Discount> discounts;

    public BigDecimal applyDiscount(final Order order) {
        log.info("Applying discounts");

        BigDecimal discountedPrice = order.getTotalPrice();
        log.info("total price: {}", discountedPrice);

        for (Discount discount : discounts) {
            discountedPrice = discount.discount(order, discountedPrice);
            log.info("price after discount: {}", discountedPrice);
        }

        return discountedPrice;
    }
}
