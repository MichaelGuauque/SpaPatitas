package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends CrudRepository<Cita, Long> {
}
