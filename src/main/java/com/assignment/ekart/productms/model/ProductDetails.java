package com.assignment.ekart.productms.model;

import lombok.Data;

@Data
public class ProductDetails {
    private Integer productId;
    private String name;
    private String description;
    private String category;
    private String brand;
    private Double price;
    private Integer availableQuantity;
}
