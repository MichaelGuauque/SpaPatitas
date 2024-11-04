package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    Optional<Producto> findProductoByNombre(String nombre);
}
