package com.contribuidor.cma.dto;

public class JwtAuthenticationResponseDto {

    private final String token;

    public JwtAuthenticationResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
