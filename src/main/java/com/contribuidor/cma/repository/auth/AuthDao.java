package com.contribuidor.cma.repository.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class AuthDao {

    @Autowired
    private EntityManager em;

    @Autowired
    private AuthRepository repository;

    public AuthRepository getRepository(){
        return repository;
    }


}
