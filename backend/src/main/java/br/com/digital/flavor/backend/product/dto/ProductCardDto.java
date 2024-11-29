package br.com.digital.flavor.backend.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCardDto(UUID id, Short stock, BigDecimal price, String name, String shortDescription,
                             String imageUrl) {
}
