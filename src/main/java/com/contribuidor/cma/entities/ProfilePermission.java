package com.contribuidor.cma.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "profile_permission")
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Profile.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profile")
    private Profile profile;

    @ManyToOne(targetEntity = Permission.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_permission")
    private Permission permission;

}
