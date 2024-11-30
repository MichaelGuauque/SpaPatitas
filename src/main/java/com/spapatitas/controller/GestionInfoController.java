package com.spapatitas.controller;

import com.spapatitas.persistence.model.GestionInfo;
import com.spapatitas.service.interfaces.IGestionInfoService;
import org.slf4j.Logger;
import org.slf4j.*;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionInfo")
public class GestionInfoController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IGestionInfoService gestionInfoService;

    //Metodo para mostrar la vista con los datos existentes
    @GetMapping()
    public String mostrarFormulario(Model model) {
        GestionInfo gestionInfo = gestionInfoService.obtenerRegistroUnico();
        model.addAttribute("gestionInfo", gestionInfo);
        return "gestionInfo/vistaGestionInfo"; // Reemplazar con el nombre del archivo HTML correspondiente
    }

    //Metodo para guardar los cambios realizados
    @PostMapping("/actualizar")
    public String actualizarGestionInfo(@ModelAttribute GestionInfo gestionInfo) {
        gestionInfoService.actualizarGestionInfo(gestionInfo);
        return "redirect:/gestionInfo?actualizado=true";
    }

    @PostMapping("/gestionInfo/actualizar-quienes-somos")
    public String actualizarQuienesSomos(@ModelAttribute GestionInfo gestionInfo) {
        GestionInfo existente = gestionInfoService.obtenerRegistroUnico();
        existente.setInformacion(gestionInfo.getInformacion());
        existente.setMision(gestionInfo.getMision());
        existente.setVision(gestionInfo.getVision());
        gestionInfoService.actualizarGestionInfo(existente);
        return "redirect:/gestionInfo"; // Redirige a la página principal del formulario
    }


    @PostMapping("/gestionInfo/actualizar-pqrs")
    public String actualizarPQRS(@ModelAttribute GestionInfo gestionInfo) {
        GestionInfo existente = gestionInfoService.obtenerRegistroUnico();
        existente.setInfoAdicional(gestionInfo.getInfoAdicional());
        existente.setContacto(gestionInfo.getContacto());
        gestionInfoService.actualizarGestionInfo(existente);
        return "redirect:/gestionInfo";
    }





}




