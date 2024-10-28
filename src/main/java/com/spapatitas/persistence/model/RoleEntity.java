package com.spapatitas.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    private Long id;
    private RoleEnum roleEnum;
    private Set<PermissionEntity> permissions = new HashSet<>();
}
