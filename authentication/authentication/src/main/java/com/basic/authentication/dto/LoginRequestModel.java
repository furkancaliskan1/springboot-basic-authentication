package com.basic.authentication.dto;

public record LoginRequestModel(
        String username,
        String password
) {
}
