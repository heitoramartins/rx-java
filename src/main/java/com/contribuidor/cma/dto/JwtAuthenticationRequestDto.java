package com.contribuidor.cma.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtAuthenticationRequestDto implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String email;
    private String password;

    public JwtAuthenticationRequestDto() {
        super();
    }

    public JwtAuthenticationRequestDto(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

}
