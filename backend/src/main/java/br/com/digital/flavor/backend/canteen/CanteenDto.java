package br.com.digital.flavor.backend.canteen;

import java.util.UUID;

public record CanteenDto(UUID id, String name, String address) {
}
