package com.pioneers.ordertask.orders.services;

import com.pioneers.ordertask.orders.dtos.OrderRequest;
import com.pioneers.ordertask.orders.dtos.OrderResponse;
import com.pioneers.ordertask.orders.models.Order;
import com.pioneers.ordertask.orders.repositories.OrderRepo;
import com.pioneers.ordertask.orders.services.strategy.DiscountProcessor;
import com.pioneers.ordertask.orders.utils.OrderMapper;
import com.pioneers.ordertask.products.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final DiscountProcessor discountProcessor;

    @Override
    public OrderResponse create(final OrderRequest request) {
        log.info("Order creation request");

        final Order order = OrderMapper.fromRequestToOrder(request);

        validateOrder(order);

        final BigDecimal discountedPrice = discountProcessor.applyDiscount(order);
        order.setPriceAfterDiscount(discountedPrice);
        log.info("discount applied");

        final Order saved = orderRepo.save(order);
        log.info("Order saved");

        saved.getItems()
                .forEach(item -> {
                    item.product().reduceStock(item.orderedQuantity());
                    productRepo.update(item.product());
                });

        log.info("Order created with id: {}", saved.getId());

        return OrderMapper.fromOrderToResponse(saved);
    }

    @Override
    public OrderResponse getById(final UUID id) {
        final Order order = orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order does not exist"));

        log.info("Order found");
        return OrderMapper.fromOrderToResponse(order);
    }

    @Override
    public List<OrderResponse> getAll() {
        log.info("Getting all orders");

        return orderRepo.findAll().stream()
                .map(OrderMapper::fromOrderToResponse)
                .toList();
    }

    private void validateOrder(final Order order) {
        order.getItems().forEach(item -> {
            final String productName = item.product().getName();

            productRepo
                    .findByName(productName)
                    .orElseThrow(() -> new IllegalStateException("product " + productName + " does not exist."));

            if (item.product().isProductLowStock(item.orderedQuantity())) {
                throw new IllegalStateException("product " + productName + " stock is not enough.");
            }

        });
        log.info("Order validated");
    }

}
