package com.spapatitas.service.implementation;

import com.spapatitas.DTO.ProductoDTO;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.Proveedor;
import com.spapatitas.persistence.repository.ProductoRepository;
import com.spapatitas.service.interfaces.IProductoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Optional<Producto> findById(Long codigo) {
        return productoRepository.findById(codigo);
    }

    @Override
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public void save(ProductoDTO productoDTO) throws SQLIntegrityConstraintViolationException, Exception {
        productoRepository.save(cambiarProductoDTO(productoDTO));
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

    @Override
    public Producto cambiarProductoDTO(ProductoDTO productoDTO) {
        Producto producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .precioPublico(productoDTO.getPrecioPublico())
                .precioProvee(productoDTO.getPrecioProvee())
                .stock(productoDTO.getStock())
                .descripcion(productoDTO.getDescripcion())
                .imagen(productoDTO.getImagen())
                .estado(productoDTO.isEstado())
                .categoria(productoDTO.getCategoria())
                .build();

        return producto;
    }

    @Override
    public List<Producto> findAllProductoHabilitados() {
        return productoRepository.findByEstadoTrue();
    }
}
