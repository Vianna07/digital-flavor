package br.com.digital.flavor.backend.order.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponseDto(UUID id, String userName, BigDecimal value, String createdAt) {
}
