package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.PromocionProducto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocionProductoRepository extends CrudRepository<PromocionProducto, Long> {
    List<PromocionProducto> findByEstadoTrue();
}
