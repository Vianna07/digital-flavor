package br.com.digital.flavor.backend.security.dto;

public record LoginResponse(String authorization, Integer expirationTime) {}
