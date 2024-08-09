package com.basic.authentication.dto;

public record RegistrationRequestModel (
        String firstName,
        String lastName,
        String username,
        String password
)
{ }
