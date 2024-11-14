package com.spapatitas.service.implementation;

import com.spapatitas.persistence.model.TipoServicio;
import com.spapatitas.persistence.repository.TipoServicioRepository;
import com.spapatitas.service.interfaces.ITipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioService implements ITipoServicioService {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Override
    public List<TipoServicio> findAllTipoServicio() {
        return (List<TipoServicio>) tipoServicioRepository.findAll();
    }

    @Override
    public Optional<TipoServicio> findById(Long id) {
        return tipoServicioRepository.findById(id);
    }

    @Override
    public void save(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }

    @Override
    public void update(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }
}