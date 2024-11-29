package br.com.digital.flavor.backend.product.dto;

import br.com.digital.flavor.backend.product.Product;

import java.math.BigDecimal;

public record ProductDetailsDto(String name, String shortDescription, String description, BigDecimal price, String imageUrl) {
    public ProductDetailsDto(Product product) {
        this(product.getName(), product.getShortDescription(),product.getDescription(), product.getPrice(), product.getImageUrl());
    }
}