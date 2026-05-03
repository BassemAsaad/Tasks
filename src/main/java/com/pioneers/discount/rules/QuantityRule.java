package com.pioneers.discount.rules;

import com.pioneers.discount.models.dtos.requests.OrderReceipt;
import com.pioneers.discount.utils.ProductItemHelper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(value = 2)
public class QuantityRule implements DiscountRule {
    @Override
    public BigDecimal applyDiscount(OrderReceipt orderReceipt, BigDecimal totalPrice) {
        boolean isDiscountMatched = orderReceipt.productItems()
                .stream()
                .anyMatch(ProductItemHelper::isItemsMoreThanFive);

        if (!isDiscountMatched) {
            return totalPrice;
        }

        return totalPrice.multiply(BigDecimal.valueOf(0.95));
    }

    /*@Override
    public int order() {
        return 2;
    }*/
}
