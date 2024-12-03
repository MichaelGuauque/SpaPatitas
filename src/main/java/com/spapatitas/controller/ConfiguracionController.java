package com.spapatitas.controller;

import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.model.Genero;
import com.spapatitas.persistence.model.UserEntity;
import com.spapatitas.service.interfaces.IClienteService;
import com.spapatitas.service.interfaces.IUserEntity;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

    private final Logger logger = LoggerFactory.getLogger(ConfiguracionController.class);

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

    private UserEntity usuarioSession(String nombreSession, HttpSession session) {
        Optional<UserEntity> optionalUserEntity = userService.findById(Long.parseLong(session.getAttribute(nombreSession).toString()));
        UserEntity usuario = optionalUserEntity.get();

        return usuario;
    }

    @GetMapping()
    public String configuracion(Model model, HttpSession session) {
        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        model.addAttribute("generos", Genero.values());
        model.addAttribute("cliente", sesionDelCliente);
        return "configuracion/vistaConfiguracionUsuario";
    }

    @GetMapping("/administrador")
    public String configuracionAdministrador(Model model, HttpSession session) {
        UserEntity usuarioSession = usuarioSession(ID_USUARIO, session);
        model.addAttribute("usuario", usuarioSession);
        return "configuracion/vistaConfiguracionAdmin";
    }

    @PostMapping("/actualizar")
    public String actualizar(Cliente cliente, UserDTO usuario) throws IOException {

//        logger.info("Cliente actualizado: {}", cliente);
        Optional<UserEntity> optionalUser = userService.findByEmail(usuario);
        UserEntity user = optionalUser.get();
        cliente.setUsuario(user);
//        logger.info("Cliente con usuario:{}",cliente);

        clienteService.update(cliente);
        return "redirect:/configuracion";
    }

    @PostMapping("/actualizarContrasenia")
    public String actualizarContrasenia(UserDTO usuario) throws IOException {

//        logger.info("Este es el usuario:{}", usuario);
        userService.updatePassword(usuario.username(),usuario.password(), usuario.newPassword());

        return "redirect:/configuracion";
    }

    @PostMapping("/actualizarContraseniaAdmin")
    public String actualizarContraseniaAdmin(UserDTO usuario) throws IOException {

//        logger.info("Este es el usuario:{}", usuario);
        userService.updatePassword(usuario.username(),usuario.password(), usuario.newPassword());

        return "redirect:/configuracion/administrador";
    }

}
