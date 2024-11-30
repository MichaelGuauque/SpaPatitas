package com.spapatitas.controller;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.*;
import com.spapatitas.service.implementation.GestionInfoService;
import com.spapatitas.service.implementation.ProductoService;
import com.spapatitas.service.implementation.TipoServicioService;
import com.spapatitas.service.interfaces.IClienteService;
import com.spapatitas.service.interfaces.IUserEntity;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserController(IUserEntity userService, IClienteService clienteService) {
        this.userService = userService;
        this.clienteService = clienteService;
    }

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TipoServicioService tipoServicioService;

    @Autowired
    private GestionInfoService gestionInfoService;


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
    public String acceder(UserDTO userDTO){
        logger.info("Usuario accedido: {}", userDTO);
        Optional<UserEntity> user = userService.findByEmail(userDTO);
        logger.info("Usuario de la BD: {}", user.get());
        return "redirect:login";
    }

    @GetMapping("/home")
    public String home(Model model){

        // Recupera todos los registros
        List<GestionInfo> gestionInfoList = gestionInfoService.findAll();

        // Verifica si hay registros
        GestionInfo gestionInfo;
        if (gestionInfoList.isEmpty()) {
            // Si no hay registros, inicializa con valores predeterminados
            gestionInfo = new GestionInfo();
        } else {
            // Toma el primer registro (suponiendo que solo hay uno relevante)
            gestionInfo = gestionInfoList.get(0);
        }

        // Agrega el objeto al modelo
        model.addAttribute("gestionInfo", gestionInfo);

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


    @GetMapping("/centroAyuda")
    public String vistaCentroAyuda(Model model) {
        // Recupera todos los registros
        List<GestionInfo> gestionInfoList = gestionInfoService.findAll();

        // Verifica si hay registros
        GestionInfo gestionInfo;
        if (gestionInfoList.isEmpty()) {
            // Si no hay registros, inicializa con valores predeterminados
            gestionInfo = new GestionInfo();
            gestionInfo.setAgendamientoCitas("No hay información disponible.");
            gestionInfo.setCompraProductos("No hay información disponible.");
            gestionInfo.setMetodosPago("No hay información disponible.");
            gestionInfo.setCuentaPerfil("No hay información disponible.");
            gestionInfo.setContactoAdicional("No hay información disponible.");
        } else {
            // Toma el primer registro (suponiendo que solo hay uno relevante)
            gestionInfo = gestionInfoList.get(0);
        }

        // Agrega el objeto al modelo
        model.addAttribute("gestionInfo", gestionInfo);
        return "info/vistaCentroAyuda";
    }


    @GetMapping("/TerminosCondiciones")
    public String vistaTerminosCondiciones(Model model) {
        // Recupera todos los registros
        List<GestionInfo> gestionInfoList = gestionInfoService.findAll();

        // Verifica si hay registros
        GestionInfo gestionInfo;
        if (gestionInfoList.isEmpty()) {
            // Si no hay registros, inicializa con valores predeterminados
            gestionInfo = new GestionInfo();
        } else {
            // Toma el primer registro (suponiendo que solo hay uno relevante)
            gestionInfo = gestionInfoList.get(0);
        }

        // Agrega el objeto al modelo
        model.addAttribute("gestionInfo", gestionInfo);
        return "info/vistaTerminosCondiciones";
    }

    @GetMapping("/pqrs")
    public String vistaPqrs(Model model) {
        // Recupera todos los registros
        List<GestionInfo> gestionInfoList = gestionInfoService.findAll();

        // Verifica si hay registros
        GestionInfo gestionInfo;
        if (gestionInfoList.isEmpty()) {
            // Si no hay registros, inicializa con valores predeterminados
            gestionInfo = new GestionInfo();
        } else {
            // Toma el primer registro (suponiendo que solo hay uno relevante)
            gestionInfo = gestionInfoList.get(0);
        }

        // Agrega el objeto al modelo
        model.addAttribute("gestionInfo", gestionInfo);
        return "info/vistaPqrs";
    }


}
