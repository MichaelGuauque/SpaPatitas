package com.spapatitas.service.implementation;

import com.spapatitas.DTO.ProveedorDTO;
import com.spapatitas.persistence.model.Proveedor;
import com.spapatitas.persistence.repository.ProveedorRepository;
import com.spapatitas.service.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService implements IProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public Optional<Proveedor> findById(Long nit) {
        return proveedorRepository.findById(nit);
    }

    @Override
    public Optional<Proveedor> findByNombre(String nombre) {
        return proveedorRepository.findProveedorByNombre(nombre);
    }

    @Override
    public List<Proveedor> findAll() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    @Override
    public void save(ProveedorDTO proveedorDTO) throws SQLIntegrityConstraintViolationException, Exception {
        proveedorRepository.save(cambiarProveedorDTO(proveedorDTO));
    }

    @Override
    public Proveedor update(Proveedor proveedor) {
        //if (proveedorRepository.existsById(proveedor.getNit())) {
            return proveedorRepository.save(proveedor);
        //}
        //throw new IllegalArgumentException("El proveedor con NIT " + proveedor.getNit() + " no existe.");
    }

    @Override
    public void deshabilitar(Long nit) {
        Optional<Proveedor> proveedor = findById(nit);
        proveedor.ifPresent(p -> {
            p.setEstado(false);
            proveedorRepository.save(p);
        });
    }

    @Override
    public void habilitar(Long nit) {
        Optional<Proveedor> proveedor = findById(nit);
        proveedor.ifPresent(p -> {
            p.setEstado(true);
            proveedorRepository.save(p);
        });
    }

    @Override
    public Proveedor cambiarProveedorDTO(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = Proveedor.builder()
                .nit(proveedorDTO.getNit())
                .nombre(proveedorDTO.getNombre())
                .telefono(proveedorDTO.getTelefono())
                .direccion(proveedorDTO.getDireccion())
                .correo(proveedorDTO.getCorreo())
                .estado(proveedorDTO.isEstado())
                .build();
        return proveedor;
    }
}
