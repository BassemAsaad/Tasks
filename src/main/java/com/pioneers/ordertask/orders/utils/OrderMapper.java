package com.pioneers.ordertask.orders.utils;

import com.pioneers.ordertask.orders.dtos.OrderRequest;
import com.pioneers.ordertask.orders.dtos.OrderResponse;
import com.pioneers.ordertask.orders.models.Order;

public final class OrderMapper {

    private  OrderMapper() {
        throw new AssertionError("You cannot instantiate this class");
    }

    public static Order fromRequestToOrder(final OrderRequest request) {
        return Order.builder()
                .customerName(request.customerName())
                .customerType(request.customerType())
                .paymentMethod(request.paymentMethod())
                .items(request.items())
                .build();
    }

    public static OrderResponse fromOrderToResponse(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .customerType(order.getCustomerType())
                .paymentMethod(order.getPaymentMethod())
                .items(order.getItems())
                .totalPrice(order.getTotalPrice())
                .priceDiscounted(order.calculateTotalDiscount())
                .priceAfterDiscount(order.getPriceAfterDiscount())
                .build();
    }
}
