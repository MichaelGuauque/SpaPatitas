package com.spapatitas.controller;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.*;
import com.spapatitas.service.implementation.GestionInfoService;
import com.spapatitas.persistence.model.*;
import com.spapatitas.service.implementation.CitaService;
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
import org.springframework.web.bind.annotation.*;

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
    private CitaService citaService;

    @Autowired
    public UserController(IUserEntity userService, IClienteService clienteService) {
        this.userService = userService;
        this.clienteService = clienteService;
    }

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TipoServicioService tipoServicioService;

    private final String ID_USUARIO = "idUsuario";

    @Autowired
    private GestionInfoService gestionInfoService;


    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/registrar")
    public String registrar(Model model) {
        model.addAttribute("generos", Genero.values());
        return "user/registrarusuario"; //hacia la vista
    }

    @PostMapping("/guardar")
    public String guardar(UserDTO usuario, ClienteDTO cliente) {
//        logger.info("Usuario registrado: {}", usuario);
//        logger.info("Cliente registrado: {}", cliente);
        UserEntity user = userService.cambioUserDTO(usuario);
        cliente.setUsuario(user);
        userService.save(user);
        clienteService.save(cliente);
        return "redirect:login";
    }

    @PostMapping("/acceder")
    public String acceder(UserDTO userDTO, HttpSession session) {
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
            if (passwordEncoder.matches(userDTO.password(), usuarioBuscado.getPassword())) {
                if (rol.equals("ADMIN")) {
                    return "redirect:/administrador/home";
                }
                return "user/homeUser";
            } else {
                return "redirect:login";
            }
        }
        logger.info("Usuario no encontrado");
        return "redirect:login";
    }

    @GetMapping("/home")
    public String home() {
        return "user/homeUser";
    }

    @GetMapping("/productos")
    public String productos(Model model) {
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

    private Cliente clienteSession(String nombreSession, HttpSession session) {
        Optional<UserEntity> optionalUserEntity = userService.findById(Long.parseLong(session.getAttribute(nombreSession).toString()));
        UserEntity usuario = optionalUserEntity.get();
        Optional<Cliente> optionalCliente = clienteService.findById(usuario.getCliente().getIdCliente());
        Cliente cliente = optionalCliente.get();
        return cliente;
    }

    @GetMapping("/citas")
    public String citas(Model model, HttpSession session) {
        List<TipoServicio> tipoServicios = tipoServicioService.findAllTipoServicio();
        List<Cita> listaCitas = citaService.findAllCitaOrdenadas();
        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        List<Cita> citas = citaService.findAllByCliente_IdCliente(sesionDelCliente.getIdCliente());
        model.addAttribute("servicios", tipoServicios);
        model.addAttribute("citas", listaCitas);
        model.addAttribute("citas", citas);
        return "citas/vistaCitasUsuario";
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

    @PostMapping("/crearCitaUsuario")
    public String crearCitaUsuario(Cita cita, HttpSession session, @RequestParam List<Long> serviciosSeleccionados) {

        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        List<TipoServicio> servicios = tipoServicioService.findByIds(serviciosSeleccionados);
        cita.setTipoServicios(servicios);
        cita.setDisponible(false);
        cita.setCliente(sesionDelCliente);
//        logger.info("Servicios encontrados: {}", servicios);
//        logger.info("Esta es la cita {}", cita);
//        logger.info("Servicios seleccionados: {}", serviciosSeleccionados);
        citaService.agendarCita(cita);
        return "redirect:/user/citas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        citaService.desagendarCita(id);
        return "redirect:/user/citas";
    }

    @GetMapping("/promociones")
    public String promociones() {
        return "promociones/promocionesConstruccionUsuario";
    }
}