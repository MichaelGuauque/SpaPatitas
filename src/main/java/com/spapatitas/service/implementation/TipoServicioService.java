package com.spapatitas.service.implementation;

import com.spapatitas.DTO.TipoServicioDTO;
import com.spapatitas.persistence.model.TipoServicio;
import com.spapatitas.persistence.repository.TipoServicioRepository;
import com.spapatitas.service.interfaces.ITipoServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
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
    public void save(TipoServicioDTO tipoServicioDTO) throws SQLIntegrityConstraintViolationException, Exception {
        tipoServicioRepository.save(cambiarTipoServicioDTO(tipoServicioDTO));
    }

    @Override
    public TipoServicio update(TipoServicio tipoServicio) {
        if (tipoServicioRepository.existsById(tipoServicio.getId())) {
            return  tipoServicioRepository.save(tipoServicio);
        }
        throw new IllegalArgumentException("El Servicio con ID " + tipoServicio.getId() + " no existe.");
}

    @Override
    public void deshabilitar(Long id) {
        Optional<TipoServicio> tipoServicio = findById(id);
        tipoServicio.ifPresent(m -> {
            m.setEstado(false);
            tipoServicioRepository.save(m);
        });
    }

    @Override
    public void habilitar(Long id) {
        Optional<TipoServicio> tipoServicio = findById(id);
        tipoServicio.ifPresent(m -> {
            m.setEstado(true);
            tipoServicioRepository.save(m);
        });
    }

    @Override
    public TipoServicio cambiarTipoServicioDTO(TipoServicioDTO tipoServicioDTO) {
        TipoServicio tipoServicio = TipoServicio.builder()
                .nombreServicio(tipoServicioDTO.getNombreServicio())
                .descripcion(tipoServicioDTO.getDescripcion())
                .precioPublico(tipoServicioDTO.getPrecioPublico())
                .costoInterno(tipoServicioDTO.getCostoInterno())
                .estado(tipoServicioDTO.isEstado())
                .build();

        return tipoServicio;
    }


}