package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.TipoServicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoServicioRepository extends CrudRepository<TipoServicio, Long> {
}
