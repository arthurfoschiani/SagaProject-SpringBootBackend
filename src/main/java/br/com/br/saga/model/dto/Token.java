package br.com.br.saga.model.dto;

public record Token(
    String token,
    String type,
    String prefix
) {}
