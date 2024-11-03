package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {
    List<Cita> findByDisponibleIsTrue();
}
