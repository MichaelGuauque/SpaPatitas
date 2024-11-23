package com.spapatitas.service.interfaces;

import com.spapatitas.persistence.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {

    List<Categoria> findAll();
    Optional<Categoria> findById(long id);
    void save(Categoria categoria);
    void update(Categoria categoria);
}
