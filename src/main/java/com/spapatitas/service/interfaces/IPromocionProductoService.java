package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.PromocionDTO;
import com.spapatitas.persistence.model.PromocionProducto;

import java.util.List;
import java.util.Optional;

public interface IPromocionProductoService {

    void save(PromocionProducto promocionProducto);
    void deshabilitar(Long id);
    void habilitar(Long id);
    List<PromocionProducto> findAll();
    List<PromocionProducto> findAllPromocionesDisponibles();
    Optional<PromocionProducto> findById(Long id);
    PromocionProducto update(PromocionProducto promocionProducto);
    PromocionProducto cambiarDTO(PromocionDTO promocionDTO);
}
