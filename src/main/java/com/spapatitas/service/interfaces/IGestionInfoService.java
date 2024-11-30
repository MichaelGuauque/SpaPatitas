package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.GestionInfoDTO;
import com.spapatitas.persistence.model.GestionInfo;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface IGestionInfoService {

    public List<GestionInfo> findAll();

    public GestionInfo obtenerRegistroUnico();

    public void actualizarGestionInfo(GestionInfo gestionInfo);

    public void save(GestionInfoDTO gestionInfoDTO) throws
            SQLIntegrityConstraintViolationException, Exception;

    public GestionInfo update(GestionInfo gestionInfo);

    GestionInfo cambiarGestionInfoDTO (GestionInfoDTO gestionInfoDTO);

}
