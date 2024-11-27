package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.TipoServicioDTO;
import com.spapatitas.persistence.model.TipoServicio;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface ITipoServicioService {

    public List<TipoServicio> findAllTipoServicio();

    public List<TipoServicio> findAllTipoServicioHabilitados();

    public List<TipoServicio> findByNombre(String nombreServicio);

    public Optional<TipoServicio> findById(Long id);

    public void save(TipoServicioDTO tipoServicioDTO) throws
            SQLIntegrityConstraintViolationException, Exception;

    public TipoServicio update(TipoServicio tipoServicio);

    public void deshabilitar(Long id);

    public void habilitar(Long id);

    TipoServicio cambiarTipoServicioDTO (TipoServicioDTO tipoServicioDTO);
}
