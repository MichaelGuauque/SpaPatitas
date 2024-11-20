package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.ProductoDTO;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.Proveedor;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface IProductoService {


    public Optional<Producto> findById (Long codigo);
    public Optional<Producto> findByNombre (String nombre);
    public List<Producto> findAll();
    public void save(ProductoDTO productoDTO) throws
            SQLIntegrityConstraintViolationException, Exception;
    public Producto update(Producto producto);
    public void deshabilitar(Long codigo);
    public void habilitar(Long codigo);
    Producto cambiarProductoDTO(ProductoDTO productoDTO);
}
