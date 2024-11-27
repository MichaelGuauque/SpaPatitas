package com.spapatitas.controller;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.DTO.MascotaDTO;
import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.*;
import com.spapatitas.service.implementation.MascotaService;
import com.spapatitas.service.interfaces.IClienteService;
import com.spapatitas.service.interfaces.IUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClientesController {


    private final Logger logger = LoggerFactory.getLogger(ClientesController.class);

    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IUserEntity userService;
    @Autowired
    private MascotaService mascotaService;


    @GetMapping()
    public String clientes(Model model) {
        model.addAttribute("generos", Genero.values());
        model.addAttribute("clientes", clienteService.findAll());
        return "clientes/vistaClientes";
    }

    @PostMapping("/guardar")
    public String guardar(UserDTO usuario, ClienteDTO cliente) {
//        logger.info("Usuario registrado: {}", usuario);
//        logger.info("Cliente registrado: {}", cliente);
        UserEntity user = userService.cambioUserDTO(usuario);
        cliente.setUsuario(user);
        userService.save(user);
        clienteService.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/guardarMascota")
    public String guardarMascota(MascotaDTO mascota, Cliente cliente) throws Exception {
        Optional<Cliente> optionalCliente = clienteService.findById(cliente.getIdCliente());
        cliente = optionalCliente.get();
        mascota.setDueno(cliente);
//        logger.info("Mascota registrada: {}", mascota);
        mascota.setDueno(cliente);
        mascotaService.save(mascota);
        return "redirect:/clientes/verMascotas/" + cliente.getIdCliente();
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = new Cliente();
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        cliente = optionalCliente.get();
        model.addAttribute("cliente", cliente);
        model.addAttribute("generos", Genero.values());
        return "clientes/vistaEditarClientes";
    }

    @GetMapping("/editarMascota/{id}")
    public String editarMascota(@PathVariable Long id, Model model) {
        Mascota mascota = new Mascota();
        Optional<Mascota> optionalMascota = mascotaService.findById(id);
        mascota = optionalMascota.get();
        model.addAttribute("mascota", mascota);
        return "clientes/vistaEditarMascotasCliente";
    }

    @PostMapping("/actualizar")
    public String actualizar(Cliente cliente, UserDTO usuario) throws IOException {

//        logger.info("Cliente actualizado: {}", cliente);
        Optional<UserEntity> optionalUser = userService.findByEmail(usuario);
        UserEntity user = optionalUser.get();
        cliente.setUsuario(user);
//        logger.info("Cliente con usuario:{}",cliente);

        clienteService.update(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/actualizarMascota")
    public String actualizarMascota(Mascota mascota) throws IOException {

        Optional<Mascota> optionalMascota = mascotaService.findById(mascota.getId());
        Mascota mascota1 = optionalMascota.get();
        mascota.setDueno(mascota1.getDueno());
        //        logger.info("Cliente actualizado: {}", cliente);
//        logger.info("Cliente con usuario:{}",cliente);

        mascotaService.update(mascota);
        return "redirect:/clientes";
    }

    @GetMapping("/verMascotas/{id}")
    public String verMascotas(@PathVariable Long id, Model model) {
        Cliente cliente = new Cliente();
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        cliente = optionalCliente.get();
        model.addAttribute("cliente", cliente);
        model.addAttribute("mascotas", mascotaService.findAllByDuenoCedula(cliente.getCedula()));
        return "clientes/vistaMascotasCliente";
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
        return "redirect:/clientes/verMascotas/" + mascota.get().getDueno().getIdCliente(); // Redirige a la vista principal de mascotas
    }

}
