package com.contribuidor.cma.repository.auth;

import com.contribuidor.cma.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u inner join u.profile pro where u.email = ?1")
    User findByEmail(String email);
}
