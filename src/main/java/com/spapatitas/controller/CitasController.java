package com.spapatitas.controller;

import com.spapatitas.persistence.model.Cita;
import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.model.TipoServicio;
import com.spapatitas.service.implementation.ClienteService;
import com.spapatitas.service.interfaces.ICitaService;
import com.spapatitas.service.interfaces.ITipoServicioService;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/citas")
public class CitasController {

    private final Logger logger = LoggerFactory.getLogger(CitasController.class);

    @Autowired
    private ICitaService citaService;
    @Autowired
    private ITipoServicioService tipoServicioService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public String servicios(Model model){
        // Obtiene la lista de servicios desde la base de datos
        List<Cliente>  clientes = clienteService.findAll();
        List<TipoServicio> tipoServicios = tipoServicioService.findAllTipoServicio();
        List<Cita> listaCitas = citaService.findAllCitaOrdenadas();
        model.addAttribute("servicios", tipoServicios);
        model.addAttribute("citas", listaCitas);
        model.addAttribute("clientes", clientes);
        return "citas/vistaCitas";
    }

    @PostMapping("/crear")
    public String crear(Cita cita, @RequestParam List<Long> serviciosSeleccionados,@RequestParam int cedula) {
        Cliente cliente1 = new Cliente();
        Optional<Cliente> cliente = clienteService.findByCedula(cedula);
        cliente1 = cliente.get();

        List<TipoServicio> servicios = tipoServicioService.findByIds(serviciosSeleccionados);
        cita.setTipoServicios(servicios);
        cita.setDisponible(false);
        cita.setCliente(cliente1);

//        logger.info("Servicios encontrados: {}", servicios);
//        logger.info("Esta es la cita {}", cita);
//        logger.info("Servicios seleccionados: {}", serviciosSeleccionados);
        citaService.agendarCita(cita, cliente1.getIdCliente());
        return "redirect:/citas";}

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        citaService.desagendarCita(id);
        return "redirect:/citas";
    }
}