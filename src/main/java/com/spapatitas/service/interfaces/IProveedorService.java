package com.spapatitas.service.interfaces;

import com.spapatitas.persistence.model.Proveedor;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface IProveedorService {

    public Optional<Proveedor> findById (Long nit);
    public Optional<Proveedor> findByNombre (String nombre);
    public List<Proveedor> findAll();
    public void save(Proveedor proveedor) throws
            SQLIntegrityConstraintViolationException, Exception;
    public Proveedor update(Proveedor proveedor);
    public void deshabilitar(Long nit);
    public void habilitar(Long nit);
}
