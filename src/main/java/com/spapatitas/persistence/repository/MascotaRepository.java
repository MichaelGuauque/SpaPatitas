package com.spapatitas.persistence.repository;

import com.spapatitas.persistence.model.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {

    //Optional<Mascota> findMascotaById(Long id);

    List<Mascota> findAllByDueno_Cedula(int cedula);

}
