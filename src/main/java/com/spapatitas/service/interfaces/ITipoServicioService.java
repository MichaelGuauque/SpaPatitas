package com.spapatitas.service.interfaces;

import com.spapatitas.persistence.model.TipoServicio;

import java.util.List;
import java.util.Optional;

public interface ITipoServicioService {

    public List<TipoServicio> findAllTipoServicio();

    public Optional<TipoServicio> findById(Long id);

    public List<TipoServicio> findByIds(List<Long> ids);

    public void save(TipoServicio tipoServicio);

    public void update(TipoServicio tipoServicio);

    // falta habilitar/deshabilitar
}
