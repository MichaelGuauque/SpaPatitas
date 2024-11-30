package com.spapatitas.service.implementation;

import com.spapatitas.DTO.GestionInfoDTO;
import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.model.GestionInfo;
import com.spapatitas.persistence.repository.GestionInfoRepository;
import com.spapatitas.service.interfaces.IGestionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class GestionInfoService implements IGestionInfoService {

    @Autowired
    private GestionInfoRepository gestionInfoRepository;

    @Override
    public List<GestionInfo> findAll() {
        return (List<GestionInfo>) gestionInfoRepository.findAll();
    }

    @Override
    public void save(GestionInfoDTO gestionInfoDTO) throws SQLIntegrityConstraintViolationException, Exception {
        gestionInfoRepository.save(cambiarGestionInfoDTO(gestionInfoDTO));
    }

    @Override
    public GestionInfo update(GestionInfo gestionInfo) {
        if (gestionInfoRepository.existsById(gestionInfo.getId())) {
            return  gestionInfoRepository.save(gestionInfo);
        }
        throw new IllegalArgumentException("La info con ID " + gestionInfo.getId() + " no existe.");
    }

    // Obtiene el único registro de GestionInfo
    @Override
    public GestionInfo obtenerRegistroUnico() {
        return gestionInfoRepository.findById(1L).orElseGet(() -> {
            // Si no existe un registro, inicializar uno con valores por defecto
            GestionInfo nuevo = new GestionInfo();
            nuevo.setId(1L); // ID fijo para garantizar unicidad
            nuevo.setInformacion("");
            nuevo.setMision("");
            nuevo.setVision("");
            // Inicializar otros campos si es necesario
            return gestionInfoRepository.save(nuevo);
        });
    }

    // Actualiza el registro existente
    @Override
    public void actualizarGestionInfo(GestionInfo gestionInfo) {
        gestionInfo.setId(1L); // Asegurarse de que no se crea un nuevo registro
        gestionInfoRepository.save(gestionInfo);
    }


    @Override
    public GestionInfo cambiarGestionInfoDTO(GestionInfoDTO gestionInfoDTO) {
        GestionInfo gestionInfo = GestionInfo.builder()
                .informacion(gestionInfoDTO.getInformacion())
                .mision(gestionInfoDTO.getMision())
                .vision(gestionInfoDTO.getVision())
                .infoAdicional(gestionInfoDTO.getInfoAdicional())
                .contacto(gestionInfoDTO.getContacto())
                .agendamientoCitas(gestionInfoDTO.getAgendamientoCitas())
                .compraProductos(gestionInfoDTO.getCompraProductos())
                .metodosPago(gestionInfoDTO.getMetodosPago())
                .cuentaPerfil(gestionInfoDTO.getCuentaPerfil())
                .contactoAdicional(gestionInfoDTO.getContactoAdicional())
                .definiciones(gestionInfoDTO.getDefiniciones())
                .usoSitioWeb(gestionInfoDTO.getUsoSitioWeb())
                .cuentasRegistro(gestionInfoDTO.getCuentasRegistro())
                .codicionesVenta(gestionInfoDTO.getCodicionesVenta())
                .condicionesAgendamiento(gestionInfoDTO.getCondicionesAgendamiento())
                .limitacionesResponsabilidad(gestionInfoDTO.getLimitacionesResponsabilidad())
                .propiedadIntelectual(gestionInfoDTO.getPropiedadIntelectual())
                .politicaPrivacidad(gestionInfoDTO.getPoliticaPrivacidad())
                .modificacionTerminos(gestionInfoDTO.getModificacionTerminos())
                .aceptacionTerminos(gestionInfoDTO.getAceptacionTerminos())
                .build();

        return gestionInfo;
    }
}
