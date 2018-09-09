package com.contribuidor.cma.service.auth;

import com.contribuidor.cma.config.security.jwt.UserSystem;
import com.contribuidor.cma.entities.ProfilePermission;
import com.contribuidor.cma.entities.User;
import com.contribuidor.cma.repository.auth.AuthDao;
import com.contribuidor.cma.repository.profilepermission.ProfilePermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Transactional
public class AuthService implements UserDetailsService {

    @Autowired
    private AuthDao dao;

    @Autowired
    private ProfilePermissionDao profilePermissionDao;

    private User user;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = dao.getRepository().findByEmail(email);
        user = usuario;
        return new UserSystem(usuario.getId(), usuario.getEmail(),
                usuario.getPassword(), authorities(usuario));
    }

    public Collection<? extends GrantedAuthority> authorities(User user) {
        Collection<GrantedAuthority> auths = new ArrayList<>();
        for (ProfilePermission pp : profilePermissionDao.getRepository().findUserByAuthorities(user.getProfile().getId())) {
            auths.add(new SimpleGrantedAuthority("ROLE_" + pp.getPermission().getName()));
        }
        return auths;
    }

    public User getUser() {
        return user;
    }
}
