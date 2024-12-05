package com.spapatitas.controller;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.*;
import com.spapatitas.service.implementation.*;
import com.spapatitas.service.interfaces.IClienteService;
import com.spapatitas.service.interfaces.IPromocionProductoService;
import com.spapatitas.service.interfaces.IUserEntity;
import com.spapatitas.service.interfaces.IVentaService;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final IUserEntity userService;
    private final IClienteService clienteService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //List<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
    Venta venta = new Venta();

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

    @Autowired
    private GestionInfoService gestionInfoService;

    @Autowired
    private IPromocionProductoService promocionService;

    @Autowired
    private IVentaService ventaService;

    @Autowired
    private ReciboPDFService reciboPDFService;

    private final String ID_USUARIO = "idUsuario";

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
    public String acceder(UserDTO userDTO, HttpSession session, Model model) {
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
                // Logica para traer la Info de "Quienes Somos"

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
            } else {
                return "redirect:login";
            }
        }
        logger.info("Usuario no encontrado");
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
    public String promociones(Model model) {
        model.addAttribute("promociones", promocionService.findAllPromocionesDisponibles());
        return "promociones/vistaPromocionesUsuario";

    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){
        session.removeAttribute("idUsuario");
        return "redirect:/user/login";
    }

    @PostMapping("/carrito")
    public String añadirCarrito(@RequestParam (required = false) Long codigo,
                                @RequestParam(defaultValue = "1") int cantidad,
                                Model model) {
        // Buscar el producto
        Producto producto = productoService.getProductoRepository().findById(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificar si el producto ya está en el carrito
        Optional<DetalleVenta> existente = venta.getDetallesVenta().stream()
                .filter(det -> det.getProducto().getCodigo().equals(producto.getCodigo()))
                .findFirst();

        if (existente.isPresent()) {
            // Actualizar cantidad y total del producto ya existente
            DetalleVenta detExistente = existente.get();
            detExistente.setCantidad(detExistente.getCantidad() + 1);
            // Calcular el nuevo total
            detExistente.setTotal(detExistente.getCantidad() * producto.getPrecioPublico());
        } else {
            // Crear un nuevo detalle
            DetalleVenta detalleVenta = new DetalleVenta(cantidad, producto);
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(cantidad);

            venta.addDetalleVenta(detalleVenta);
        }

        // Pasar datos al modelo
        model.addAttribute("carrito", venta.getDetallesVenta());
        model.addAttribute("sumaSubTotal", venta.getValorSinIva()); // SubTotal acumulado del carrito
        model.addAttribute("ivaComp", venta.getValorIva()); // Iva de la compra del carrito
        model.addAttribute("sumaTotal", venta.getTotal()); // Total compra
        model.addAttribute("venta", venta);

        return "user/carrito";
    }

    @GetMapping("/delete/carrito/{codigo}")
    public String borrarProductoCarrito(@PathVariable Long codigo, Model model){

        // Buscar detalle a eliminar
        DetalleVenta detalleEliminar = venta.getDetallesVenta().stream()
                .filter(det ->det.getProducto().getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
        if (detalleEliminar != null) {
            venta.removeDetalleVenta(detalleEliminar);
        }

        // Pasar datos al modelo
        model.addAttribute("carrito", venta.getDetallesVenta());
        model.addAttribute("sumaSubTotal", venta.getValorSinIva()); // SubTotal del carrito
        model.addAttribute("ivaComp", venta.getValorIva()); // Iva de la compra del carrito
        model.addAttribute("sumaTotal", venta.getTotal()); // Total compra
        model.addAttribute("venta", venta);

        return "user/carrito";
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {

        // Pasar datos al modelo
        model.addAttribute("carrito", venta.getDetallesVenta());
        model.addAttribute("sumaSubTotal", venta.getValorSinIva()); // SubTotal del carrito
        model.addAttribute("ivaComp", venta.getValorIva()); // Iva de la compra del carrito
        model.addAttribute("sumaTotal", venta.getTotal()); // Total compra
        model.addAttribute("venta", venta);

        return "user/carrito";
    }

//    @GetMapping("/guardar-venta")
//    public String guardarVenta(HttpSession session ) {
//        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
//        venta.setCliente(sesionDelCliente);
//        venta.setFechaVenta(LocalDateTime.now());
//        logger.info("venta: {}", venta);
//
//        ventaService.save(venta);
//        venta = new Venta();
//        return "redirect:/user/productos";
//    }

    @GetMapping("/guardar-venta")
    public String guardarVenta(HttpSession session) {
        // Obtener el cliente de la sesión
        Cliente sesionDelCliente = clienteSession(ID_USUARIO, session);
        venta.setCliente(sesionDelCliente);
        venta.setFechaVenta(LocalDateTime.now());
        logger.info("Venta: {}", venta);

        // Guardar la venta en la base de datos
        ventaService.save(venta);

        try {
            // Ruta para guardar el recibo en la carpeta de Descargas
            String userHome = System.getProperty("user.home"); // Carpeta base del usuario
            String downloadsPath = userHome + "/Downloads/recibo_" + venta.getIdVenta() + ".pdf"; // Nombre del archivo

            // Llamar al método de generación de recibo, pasando la ruta de guardado
            try (FileOutputStream fos = new FileOutputStream(downloadsPath)) {
                reciboPDFService.generarReciboPDF(venta.getIdVenta(), fos);
                logger.info("Recibo generado y guardado en: {}", downloadsPath);
            }
        } catch (IOException e) {
            logger.error("Error al generar el recibo de la venta: {}", e.getMessage(), e);
        }

        // Limpiar la venta para futuras operaciones
        venta = new Venta();

        // Redirigir al usuario de vuelta a la página de productos
        return "redirect:/user/productos";
    }





}