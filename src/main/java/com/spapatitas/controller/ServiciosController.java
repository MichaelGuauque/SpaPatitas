package com.spapatitas.controller;


import com.spapatitas.DTO.MascotaDTO;
import com.spapatitas.DTO.TipoServicioDTO;
import com.spapatitas.persistence.model.Mascota;
import com.spapatitas.persistence.model.TipoServicio;
import com.spapatitas.service.interfaces.ITipoServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/servicios")
public class ServiciosController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ITipoServicioService tipoServicioService;

    @GetMapping()
    public String servicios(Model model) {
        List<TipoServicio> tipoServicio = tipoServicioService.findAllTipoServicio();
        model.addAttribute("servicios", tipoServicioService.findAllTipoServicio());
        return "servicios/vistaServicios";
    }

    @PostMapping("/crear")
    public String crear(TipoServicioDTO tipoServicioDTO) throws Exception {
        //Logger.info("Este es el objeto servicio {}",servicio);
        tipoServicioService.save(tipoServicioDTO);
        return "redirect:/servicios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        TipoServicio tipoServicio = new TipoServicio();
        Optional<TipoServicio> optionalTipoServicio = tipoServicioService.findById(id);
        tipoServicio = optionalTipoServicio.get();
        model.addAttribute("servicio", tipoServicio);
        return "servicios/vistaEditarServicios";
    }

    @PostMapping("/actualizar")
    public String actualizar(TipoServicio tipoServicio) {

        tipoServicioService.update(tipoServicio);
        return "redirect:/servicios";
    }

    @PostMapping("/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        Optional<TipoServicio> tipoServicio = tipoServicioService.findById(id);
        if (tipoServicio.isPresent()) {
            if (tipoServicio.get().isEstado()) {
                tipoServicioService.deshabilitar(id); // Deshabilita si está habilitada
            } else {
                tipoServicioService.habilitar(id); // Habilita si está deshabilitada
            }
        }
        return "redirect:/servicios"; // Redirige a la vista principal
    }

    @GetMapping("/buscar")
    public String buscarServicio(@RequestParam("id") Long id, Model model) {
        Optional<TipoServicio> servicio = tipoServicioService.findById(id);

        if (servicio.isPresent()) {
            model.addAttribute("servicios", List.of(servicio.get())); // Lista con un único servicio
        } else {
            model.addAttribute("servicios", tipoServicioService.findAllTipoServicio()); // Todos los servicios
            model.addAttribute("error", "Servicio no encontrado con el código: " + id);
        }

        return "servicios/vistaServicios"; // Devuelve la misma vista
    }

    @GetMapping("/search")
    public String buscarServicio(@RequestParam String nombreServicio, Model model) {
        // Filtrar los servicios que coincidan con el nombre
        List<TipoServicio> serviciosEncontrados = tipoServicioService.findByNombre(nombreServicio);
        model.addAttribute("servicios", serviciosEncontrados);
        return "servicios/vistaServiciosUsuario"; // O la vista donde quieres mostrar los resultados
    }


}
