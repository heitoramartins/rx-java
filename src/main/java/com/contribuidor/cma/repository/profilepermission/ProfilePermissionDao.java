package com.contribuidor.cma.repository.profilepermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ProfilePermissionDao {

    @Autowired
    private EntityManager em;

    @Autowired
    private ProfilePermissionRepository repository;

    public ProfilePermissionRepository getRepository(){
        return repository;
    }
}
