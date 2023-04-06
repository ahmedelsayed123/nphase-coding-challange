package com.nphase.service;

import com.nphase.configuration.Configuration;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(product -> product.getPricePerUnit()
                        .multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal rewardClientAndCalculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(product -> product.getQuantity() > 3
                        ? product.getPricePerUnit()
                        .multiply(BigDecimal.valueOf(product.getQuantity()))
                        .multiply(BigDecimal.valueOf((100 - Double.valueOf(Configuration.getInstance()
                                .getProperty("discountPercentage"))) / 100))
                        : product.getPricePerUnit()
                                .multiply(BigDecimal.valueOf(product.getQuantity()))
                )
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal rewardClientForEachCategoryAndCalculateTotalPrice(ShoppingCart shoppingCart) {
        Map<String, List<Product>> products = shoppingCart.getProducts()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        return products.values()
                .stream()
                .map(this::totalPerCategory)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private BigDecimal totalPerCategory(List<Product> products) {

        BigDecimal sum = products.stream()
                .map(product -> product.getPricePerUnit()
                        .multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int quantity = products.stream()
                .mapToInt(Product::getQuantity)
                .sum();

        return quantity > 3 ? sum.multiply(BigDecimal.valueOf((100 - Double.valueOf(Configuration.getInstance()
                .getProperty("discountPercentage"))) / 100)) : sum;
    }
}
