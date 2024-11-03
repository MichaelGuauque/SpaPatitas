package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.model.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {

    //Optional<Mascota> findMascotaById(Long id);

    Optional<List<Mascota>> findAllByDueno_Cedula(int cedula);

    Optional<List<Mascota>> findAllByDueno(int cedula);
}
