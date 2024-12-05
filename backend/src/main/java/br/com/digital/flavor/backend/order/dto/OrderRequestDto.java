package br.com.digital.flavor.backend.order.dto;

import br.com.digital.flavor.backend.order_item.dto.OrderItemDto;

import java.util.List;

public record OrderRequestDto(List<OrderItemDto> orderItemsDto, String paymentMethod) {
}
