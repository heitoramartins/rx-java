package com.contribuidor.cma.repository.profilepermission;

import com.contribuidor.cma.entities.ProfilePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilePermissionRepository extends JpaRepository<ProfilePermission, Long> {

    @Query("SELECT pp FROM ProfilePermission pp inner join fetch pp.permission perm inner join fetch pp.profile perf where perf.id = ?1")
    List<ProfilePermission> findUserByAuthorities(Long codePerfil);
}
