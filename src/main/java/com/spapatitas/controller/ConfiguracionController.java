package com.spapatitas.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping()
    public String configuracion(Model model, HttpSession session) {
        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        model.addAttribute("generos", Genero.values());
        model.addAttribute("cliente", sesionDelCliente);
        return "configuracion/vistaConfiguracionUsuario";
    }

}
