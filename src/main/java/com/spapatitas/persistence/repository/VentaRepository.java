package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Venta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {

}
