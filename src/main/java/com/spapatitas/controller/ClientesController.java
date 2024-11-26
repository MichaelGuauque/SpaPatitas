package com.spapatitas.controller;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.model.Genero;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.UserEntity;
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

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Cliente cliente = new Cliente();
        Optional<Cliente> optionalCliente = clienteService.findById(id);
        cliente = optionalCliente.get();
        model.addAttribute("cliente", cliente);
        model.addAttribute("generos", Genero.values());
        return "clientes/vistaEditarClientes";
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

}
