package com.spapatitas.service.interfaces;

import com.spapatitas.persistence.model.Cita;

import java.util.List;
import java.util.Optional;

public interface ICitaService {

    public List<Cita> findAllCita();

    public Optional<Cita> findById(Long id);

    public List<Cita> findCitasDisponibles();

    public List<Cita> findAllCitaOrdenadas();

//    public void save(Cita cita);

    public void agendarCita(Cita cita );

    public void desagendarCita(Long idCita);

    public List<Cita> findAllByCliente_IdCliente(Long idCliente);
}
