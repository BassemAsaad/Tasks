package com.pioneers.discount.utils;


import com.pioneers.discount.models.dtos.requests.ProductItem;
import com.pioneers.discount.utils.categories.Category;

import java.math.BigDecimal;

public final class ProductItemHelper {

    private ProductItemHelper() {
        throw new AssertionError("Utility class");
    }

    public static boolean isItemsMoreThanFive(final ProductItem productItem) {
        return productItem.quantity() > 5;
    }

    public static boolean isProductLaptop(final ProductItem productItem) {
        return Category.ELECTRONICS.equals(productItem.category())
                && productItem.productName().toLowerCase().contains("laptop");
    }

    public static boolean isPriceGreaterFiftyThousand(BigDecimal totalPrice) {
        return totalPrice.compareTo(new BigDecimal(50000)) < 0;
    }
}
