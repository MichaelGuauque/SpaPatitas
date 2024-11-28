package com.spapatitas.controller;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.*;
import com.spapatitas.service.implementation.ProductoService;
import com.spapatitas.service.implementation.TipoServicioService;
import com.spapatitas.service.interfaces.IClienteService;
import com.spapatitas.service.interfaces.IUserEntity;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final IUserEntity userService;
    private final IClienteService clienteService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserController(IUserEntity userService, IClienteService clienteService) {
        this.userService = userService;
        this.clienteService = clienteService;
    }

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TipoServicioService tipoServicioService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("generos", Genero.values());
        return "user/registrarusuario"; //hacia la vista
    }

    @PostMapping("/guardar")
    public String guardar(UserDTO usuario , ClienteDTO cliente){
//        logger.info("Usuario registrado: {}", usuario);
//        logger.info("Cliente registrado: {}", cliente);
        UserEntity user = userService.cambioUserDTO(usuario);
        cliente.setUsuario(user);
        userService.save(user);
        clienteService.save(cliente);
        return "redirect:login";
    }

    @PostMapping("/acceder")
    public String acceder(UserDTO userDTO, HttpSession session){
        logger.info("Usuario accedido: {}", userDTO);
        Optional<UserEntity> user = userService.findByEmail(userDTO);
        if (user.isPresent()) {
            UserEntity usuarioBuscado = user.get();
            logger.info("Usuario de la BD: {}", usuarioBuscado);
            session.setAttribute("idUsuario", usuarioBuscado.getId());
            Optional<RoleEnum> optionalRol = usuarioBuscado.getRoles().stream()
                    .map(RoleEntity::getRoleEnum)
                    .findFirst();
            RoleEnum rolEnum = optionalRol.get();
            String rol = rolEnum.name();
            logger.info("Rol del usuario: {}", rol);
            if(passwordEncoder.matches(userDTO.password(), usuarioBuscado.getPassword())){
                if(rol.equals("ADMIN")){
                    return "redirect:/administrador/home";
                }
                return "user/homeUser";
            }else {
                return "redirect:login";
            }
        }
        logger.info("Usuario no encontrado");
        return "redirect:login";
    }

    @GetMapping("/home")
    public String home(){
        return "user/homeUser";
    }

    @GetMapping("/productos")
    public String productos(Model model){
        List<Producto> productos = productoService.findAllProductoHabilitados();
        model.addAttribute("productos", productos);
        return "productos/vistaProductosUsuario";
    }

    @GetMapping("/servicios")
    public String servicios(Model model) {
        List<TipoServicio> tipoServicio = tipoServicioService.findAllTipoServicioHabilitados();
        model.addAttribute("servicios", tipoServicio);
        return "servicios/vistaServiciosUsuario";
    }

}
