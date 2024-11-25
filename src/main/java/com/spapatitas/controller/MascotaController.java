package com.spapatitas.controller;

import com.spapatitas.DTO.MascotaDTO;
import com.spapatitas.persistence.model.Mascota;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.service.interfaces.IMascotaService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IMascotaService mascotaService;

    @GetMapping()
    public String mascotas(Model model) {
        List<Mascota> mascotas = mascotaService.findAll();
        model.addAttribute("mascotas", mascotaService.findAll());
        return "mascotas/vistaMascotasUsuario";
    }

    @PostMapping("/crear")
    public String crear(MascotaDTO mascotaDTO) throws Exception {
        //Logger.info("Este es el objeto mascota {}",mascota);
        mascotaService.save(mascotaDTO);
        return "redirect:/mascotas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Mascota mascota = new Mascota();
        Optional<Mascota> optionalMascota = mascotaService.findById(id);
        mascota = optionalMascota.get();
        model.addAttribute("mascota", mascota);
        return "mascotas/vistaEditarMascotas";
    }

    @PostMapping("/actualizar")
    public String actualizar(Mascota mascota) {

        mascotaService.update(mascota);
        return "redirect:/mascotas";
    }

    @PostMapping("/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        Optional<Mascota> mascota = mascotaService.findById(id);
        if (mascota.isPresent()) {
            if (mascota.get().isEstado()) {
                mascotaService.deshabilitar(id); // Deshabilita si está habilitada
            } else {
                mascotaService.habilitar(id); // Habilita si está deshabilitada
            }
        }
        return "redirect:/mascotas"; // Redirige a la vista principal de mascotas
    }
}


