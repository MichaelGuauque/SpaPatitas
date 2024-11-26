package com.spapatitas.controller;

import com.spapatitas.persistence.model.Cita;
import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.model.TipoServicio;
import com.spapatitas.service.interfaces.ICitaService;
import com.spapatitas.service.interfaces.ITipoServicioService;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitasController {

    private final Logger logger = LoggerFactory.getLogger(CitasController.class);

    @Autowired
    private ICitaService citaService;
    @Autowired
    private ITipoServicioService tipoServicioService;

    @GetMapping()
    public String servicios(Model model){
        // Obtiene la lista de servicios desde la base de datos
        List<TipoServicio> tipoServicios = tipoServicioService.findAllTipoServicio();
        model.addAttribute("servicios", tipoServicios);
        return "citas/vistaCitas";
    }

    @PostMapping("/crear")
    public String crear(Cita cita, @RequestParam List<Long> serviciosSeleccionados) {
        logger.info("Esta es la cita {}", cita);
        logger.info("Servicios seleccionados: {}", serviciosSeleccionados);
        List<TipoServicio> servicios = tipoServicioService.findByIds(serviciosSeleccionados);
        logger.info("Servicios encontrados: {}", servicios);
        cita.setTipoServicios(servicios);
        cita.setDisponible(false);
        citaService.agendarCita(cita, null);
        return "redirect:/citas";}
}