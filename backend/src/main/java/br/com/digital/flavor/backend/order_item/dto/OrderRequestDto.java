package br.com.digital.flavor.backend.order_item.dto;

import java.util.List;

public record OrderRequestDto(List<OrderItemDto> orderItemsDto, String paymentMethod) {
}
