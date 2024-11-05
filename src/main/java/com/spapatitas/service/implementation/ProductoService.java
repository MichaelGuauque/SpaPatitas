package com.spapatitas.service.implementation;

import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.Proveedor;
import com.spapatitas.persistence.repository.ProductoRepository;
import com.spapatitas.service.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Optional<Producto> findById(Long codigo) {
        return productoRepository.findById(codigo);
    }

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return productoRepository.findProductoByNombre(nombre);
    }

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public void save(Producto producto) throws SQLIntegrityConstraintViolationException, Exception {
        productoRepository.save(producto);
    }

    @Override
    public Producto update(Producto producto) {
        if (productoRepository.existsById(producto.getCodigo())) {
            return productoRepository.save(producto);
        }
        throw new IllegalArgumentException("El producto con codigo" + producto.getCodigo() + " no existe.");
    }

    @Override
    public void deshabilitar(Long codigo) {
        Optional<Producto> producto = findById(codigo);
        producto.ifPresent(p -> {
            p.setEstado(false);
            productoRepository.save(p);
        });
    }

    @Override
    public void habilitar(Long codigo) {
        Optional<Producto> producto = findById(codigo);
        producto.ifPresent(p -> {
            p.setEstado(true);
            productoRepository.save(p);
        });
    }
}
