package com.nphase.entity;

import java.math.BigDecimal;

public class Product {
    //we could use builder design pattern as well to be flexible adding any number of fields we want and don't need to
    // create new constructor each time or modify the existing one but for now we only modified the entity with one field
    // as required so didn't implement it but approached why we could use it
    private final String name;
    private final BigDecimal pricePerUnit;
    private final int quantity;
    private final String category;

    public Product(String name, BigDecimal pricePerUnit, int quantity, String category) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }
}
