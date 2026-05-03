package com.pioneers.discount.rules;


import com.pioneers.discount.models.dtos.requests.OrderReceipt;

import java.math.BigDecimal;

public interface DiscountRule {

    BigDecimal applyDiscount(OrderReceipt orderReceipt, BigDecimal totalPrice);

//    int order();
}
