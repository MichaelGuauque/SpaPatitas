package com.spapatitas.service.implementation;

import com.spapatitas.DTO.MascotaDTO;
import com.spapatitas.persistence.model.Mascota;
import com.spapatitas.persistence.repository.MascotaRepository;
import com.spapatitas.service.interfaces.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
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


    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }


    // Encuentra una mascota por su ID
    @Override
    public Optional<Mascota> findById(Long id) {
        Optional<Mascota> mascotaBuscada = mascotaRepository.findById(id);
         if (mascotaBuscada.isPresent()){
             return mascotaBuscada;
    }   return null;
    }


    // Guarda una nueva mascota en la base de datos
    @Override
    public void save(MascotaDTO mascotaDTO) throws SQLIntegrityConstraintViolationException, Exception {
         mascotaRepository.save(cabiarMascotaDTO(mascotaDTO));
    }


    // Actualiza una mascota existente
    @Override
    public Mascota update(Mascota mascota) {
        if (mascotaRepository.existsById(mascota.getId())) {
            return  mascotaRepository.save(mascota);
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

    @Override
    public Mascota cabiarMascotaDTO(MascotaDTO mascotaDTO) {
        Mascota mascota = Mascota.builder()
                .nombre(mascotaDTO.getNombre())
                .raza(mascotaDTO.getRaza())
                .observaciones(mascotaDTO.getObservaciones())
                .estado(mascotaDTO.isEstado())
                .dueno(mascotaDTO.getDueno())
                .fechaNacimiento(mascotaDTO.getFechaNacimiento())
                .build();

        return mascota;
    }
}
