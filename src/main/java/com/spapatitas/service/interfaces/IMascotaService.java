package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.MascotaDTO;
import com.spapatitas.persistence.model.Mascota;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface IMascotaService {

    // Encuentra todas las mascotas por Cliente(Dueño)
    public List<Mascota> findAllByDuenoCedula(int cedula);

    public List<Mascota> findAll();

    // Encuentra una mascota por su ID
    public Optional<Mascota> findById(Long id);

    // Guarda una nueva mascota en la base de datos
    public void save(MascotaDTO mascotaDTO) throws
    SQLIntegrityConstraintViolationException, Exception;

    // Actualiza una mascota existente
    public Mascota update(Mascota mascota);

    // Deshabilita una mascota (ej. setea un estado de inactivo)
    public void deshabilitar(Long id);

    // Habilita una mascota previamente deshabilitada
    public void habilitar(Long id);

    Mascota cambiarMascotaDTO (MascotaDTO mascotaDTO);

}
