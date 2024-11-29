package br.com.digital.flavor.backend.product.dto;

import java.math.BigDecimal;

public record NewProductDto(String name, String shortDescription, BigDecimal price, Short stock, String description, String imageUrl) {
}
