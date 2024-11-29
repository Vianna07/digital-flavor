package br.com.digital.flavor.backend.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDto(UUID id, Short stock, BigDecimal price, String name, String shortDescription,
                         String imageUrl) {
}
