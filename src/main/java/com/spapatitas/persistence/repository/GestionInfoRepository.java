package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.GestionInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionInfoRepository extends CrudRepository<GestionInfo, Long> {

}
