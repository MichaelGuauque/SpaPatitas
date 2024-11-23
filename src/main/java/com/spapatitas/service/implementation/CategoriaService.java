package com.spapatitas.service.implementation;

import com.spapatitas.persistence.model.Categoria;
import com.spapatitas.persistence.repository.CategoriaRepository;
import com.spapatitas.service.interfaces.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void update(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
}
