package com.spapatitas.service.implementation;

import com.spapatitas.persistence.model.Cita;
import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.repository.CitaRepository;
import com.spapatitas.persistence.repository.ClienteRepository;
import com.spapatitas.service.interfaces.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class CitaService implements ICitaService {

    @Autowired
    private CitaRepository citaRepository;
    private ClienteRepository clienteRepository;

    @Override
    public List<Cita> findAllCita() {
        return (List<Cita>) citaRepository.findAll();
    }

    @Override
    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

//    @Override
//    public void save(Cita cita) {
//        citaRepository.save(cita);
//    }


    // Obtener solo las citas donde estado es true (disponibles)
    @Override
    public List<Cita> findCitasDisponibles() {
        return citaRepository.findByDisponibleIsTrue();
    }

    @Override
    public void agendarCita(Long idCita, Long idCliente) {
        Cita cita = citaRepository.findById(idCita).orElseThrow();
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cita.getDisponible() == TRUE) {  // Revisa si la cita está disponible (true)
            cita.setDisponible(FALSE);  // Marca como ocupada (false)
            cita.setCliente(cliente.get());
            citaRepository.save(cita);
        }
        throw new RuntimeException("La cita no está disponible");
    }

    @Override
    public void desagendarCita(Long id) {
        Cita cita = citaRepository.findById(id).orElseThrow();
        if (cita.getDisponible() == FALSE) {  // Revisa si la cita está disponible (true)
            cita.setDisponible(TRUE);  // Marca como ocupada (false)
            cita.setCliente(null);
            citaRepository.save(cita);
        }
        throw new RuntimeException("La cita está disponible");
    }
}
