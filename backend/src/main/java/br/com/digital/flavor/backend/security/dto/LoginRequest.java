package br.com.digital.flavor.backend.security.dto;

public record LoginRequest(String email, String password, String canteenId) {
}
