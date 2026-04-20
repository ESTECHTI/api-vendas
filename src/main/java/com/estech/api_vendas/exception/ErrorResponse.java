package com.estech.api_vendas.exception;

public record ErrorResponse(
    String mensagem,
    int status) {
}
