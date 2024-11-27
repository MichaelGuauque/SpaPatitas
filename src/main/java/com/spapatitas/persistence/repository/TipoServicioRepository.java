package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Mascota;
import com.spapatitas.persistence.model.TipoServicio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoServicioRepository extends CrudRepository<TipoServicio, Long> {

    List<TipoServicio> findByEstadoTrue();


    List<TipoServicio> findByNombreServicioContainingIgnoreCase(String nombreServicio);


}
