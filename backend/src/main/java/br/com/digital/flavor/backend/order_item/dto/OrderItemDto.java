package br.com.digital.flavor.backend.order_item.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemDto(UUID productId, Short quantity, BigDecimal price) {
}
