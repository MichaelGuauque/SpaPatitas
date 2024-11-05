package com.spapatitas.service.implementation;

import com.spapatitas.persistence.model.Mascota;
import com.spapatitas.persistence.repository.MascotaRepository;
import com.spapatitas.service.interfaces.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    //Implementacion de Metodos

    // Encuentra todas las mascotas asociadas a un cliente usando la cédula del cliente
    @Override
    public List<Mascota> findAllByDuenoCedula(int cedula) {
        return mascotaRepository.findAllByDueno_Cedula(cedula);
    }

    // Encuentra una mascota por su ID
    @Override
    public Optional<Mascota> findById(Long id) {
        return mascotaRepository.findById(id);
    }

    // Guarda una nueva mascota en la base de datos
    @Override
    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    // Actualiza una mascota existente
    @Override
    public Mascota update(Mascota mascota) {
        if (mascota.getId() != null && mascotaRepository.existsById(mascota.getId())) {
            return mascotaRepository.save(mascota);
        }
        throw new IllegalArgumentException("La mascota con ID " + mascota.getId() + " no existe.");
    }

    // Deshabilita una mascota (marcar como inactiva)
    @Override
    public void deshabilitar(Long id) {
        Optional<Mascota> mascota = findById(id);
        mascota.ifPresent(m -> {
            m.setEstado(false);
            mascotaRepository.save(m);
        });
    }

    // Habilita una mascota previamente deshabilitada (marcar como activa)
    @Override
    public void habilitar(Long id) {
        Optional<Mascota> mascota = findById(id);
        mascota.ifPresent(m -> {
            m.setEstado(true);
            mascotaRepository.save(m);
        });
    }
}
