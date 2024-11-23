package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository  extends CrudRepository<Categoria, Long> {
}
