package com.spapatitas.service.interfaces;

import com.spapatitas.persistence.model.Mascota;

import java.util.List;
import java.util.Optional;

public interface IMascotaService {

    // Encuentra todas las mascotas por Cliente(Dueño)
    List<Mascota> findAllByDuenoCedula(int cedula);

    // Encuentra una mascota por su ID
    Optional<Mascota> findById(Long id);

    // Guarda una nueva mascota en la base de datos
    Mascota save(Mascota mascota);

    // Actualiza una mascota existente
    Mascota update(Mascota mascota);

    // Deshabilita una mascota (ej. setea un estado de inactivo)
    void deshabilitar(Long id);

    // Habilita una mascota previamente deshabilitada
    void habilitar(Long id);

}
