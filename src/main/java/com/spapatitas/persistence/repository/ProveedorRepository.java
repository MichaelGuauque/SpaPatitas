package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, Long> {

    Optional<Proveedor> findProveedorByNit(Long nit);
}