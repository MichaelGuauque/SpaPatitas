package com.spapatitas.controller;

import com.spapatitas.DTO.MascotaDTO;
import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.model.Mascota;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.UserEntity;
import com.spapatitas.service.interfaces.IClienteService;
import com.spapatitas.service.interfaces.IMascotaService;
import com.spapatitas.service.interfaces.IUserEntity;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/mascotas")
public class MascotaController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IMascotaService mascotaService;
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IUserEntity userService;

    private final String ID_USUARIO = "idUsuario";

    private Cliente clienteSession(String nombreSession, HttpSession session) {
        Optional<UserEntity> optionalUserEntity = userService.findById(Long.parseLong(session.getAttribute(nombreSession).toString()));
        UserEntity usuario = optionalUserEntity.get();
        Optional<Cliente> optionalCliente = clienteService.findById(usuario.getCliente().getIdCliente());
        Cliente cliente = optionalCliente.get();
        return cliente;
    }

    @GetMapping()
    public String mascotas(Model model, HttpSession session) {
        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        List<Mascota> mascotas = mascotaService.findAllByDuenoCedula(sesionDelCliente.getCedula());
        model.addAttribute("mascotas", mascotas);
        return "mascotas/vistaMascotasUsuario";
    }

    @PostMapping("/crear")
    public String crear(MascotaDTO mascotaDTO, HttpSession session) throws Exception {
        //Logger.info("Este es el objeto mascota {}",mascota);
        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        mascotaDTO.setDueno(sesionDelCliente);
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
    public String actualizar(Mascota mascota, HttpSession session) {
        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        mascota.setDueno(sesionDelCliente);
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


