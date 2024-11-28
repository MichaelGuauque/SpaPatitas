package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {
    List<Cita> findByDisponibleIsTrue();

    List<Cita> findAllByCliente_IdCliente(Long idCliente);

    @Query("SELECT c FROM Cita c WHERE c.fechaCita >= CURRENT_DATE ORDER BY c.fechaCita ASC, c.horaCita ASC")
    public List<Cita> findAllByOrderByFechaCitaAscHoraCitaAsc();
}
