package com.nphase.service;

import com.nphase.configuration.Configuration;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    public void calculatesPrice() {

        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(Double.valueOf(Configuration.getInstance()
                        .getProperty("teaAmount"))), 2, "drink"),
                new Product("Coffee", BigDecimal.valueOf(Double.valueOf(Configuration.getInstance()
                        .getProperty("coffeeAmount"))), 1, "drink")
        ));

        BigDecimal result = service.calculateTotalPrice(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(13.5));
    }

    @Test
    void rewardClientAndCalculateTotalPrice() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(Double.valueOf(Configuration.getInstance()
                        .getProperty("teaAmount"))), 5, "drink"),
                new Product("Coffee", BigDecimal.valueOf(Double.valueOf(Configuration.getInstance()
                        .getProperty("coffeeAmount"))), 3, "drink")
        ));

        BigDecimal result = service.rewardClientAndCalculateTotalPrice(cart);
        Assertions.assertEquals(result.setScale(1), BigDecimal.valueOf(33.0));
    }

    @Test
    void rewardClientForEachCategoryAndCalculateTotalPrice() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(Double.valueOf(Configuration.getInstance()
                        .getProperty("teaAmount"))), 2, "drinks"),
                new Product("coffee", BigDecimal.valueOf(Double.valueOf(Configuration.getInstance()
                        .getProperty("coffeeAmount"))), 2, "drinks"),
                new Product("cheese", BigDecimal.valueOf(Double.valueOf(Configuration.getInstance()
                        .getProperty("cheeseAmount"))), 2, "food")
        ));

        BigDecimal result = service.rewardClientForEachCategoryAndCalculateTotalPrice(cart);
        Assertions.assertEquals(result.setScale(1), BigDecimal.valueOf(31.3));
    }

}