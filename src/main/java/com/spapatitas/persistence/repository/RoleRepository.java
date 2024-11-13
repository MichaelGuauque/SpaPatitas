package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.RoleEntity;
import com.spapatitas.persistence.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    RoleEntity findByRoleEnum(RoleEnum role);
}
