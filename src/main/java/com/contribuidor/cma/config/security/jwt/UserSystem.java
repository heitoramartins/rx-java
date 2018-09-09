package com.contribuidor.cma.config.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserSystem extends User {

    private static final long serialVersionUID = 1L;

    private Long id;


    public UserSystem(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }
    public Long getId() {
        return id;
    }

}