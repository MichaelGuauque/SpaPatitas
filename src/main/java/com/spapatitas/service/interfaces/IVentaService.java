package com.spapatitas.service.interfaces;

import com.spapatitas.persistence.model.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    public List<Venta> findAllVenta();

    public Optional<Venta> findById(Long id);

    public void save(Venta venta);

}
