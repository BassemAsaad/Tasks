package com.pioneers.discount.services;

import com.pioneers.discount.models.dtos.requests.OrderReceipt;

import java.math.BigDecimal;

public interface DiscountService {

    BigDecimal applyDiscount(OrderReceipt orderReceipt);
}
