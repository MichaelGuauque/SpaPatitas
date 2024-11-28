package com.spapatitas.controller;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.DTO.UserDTO;
import com.spapatitas.persistence.model.*;
import com.spapatitas.service.implementation.ProductoService;
import com.spapatitas.service.interfaces.IClienteService;
import com.spapatitas.service.interfaces.IUserEntity;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final IUserEntity userService;
    private final IClienteService clienteService;

    List<DetalleVenta> detalles = new ArrayList<DetalleVenta>();
    Venta venta = new Venta();

    @Autowired
    public UserController(IUserEntity userService, IClienteService clienteService) {
        this.userService = userService;
        this.clienteService = clienteService;
    }

    @Autowired
    private ProductoService productoService;

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
    public String home(){
        return "user/homeUser";
    }

    @GetMapping("/productos")
    public String productos(Model model){
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "productos/vistaProductosUsuario";
    }

    @PostMapping("/carrito")
    public String añadirCarrito(@RequestParam (required = false) Long codigo,
                                @RequestParam(defaultValue = "1") int cantidad,
                                Model model) {
        // Buscar el producto
        Producto producto = productoService.getProductoRepository().findById(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificar si el producto ya está en el carrito
        Optional<DetalleVenta> existente = detalles.stream()
                .filter(det -> det.getProducto().getCodigo().equals(producto.getCodigo()))
                .findFirst();

        if (existente.isPresent()) {
            // Actualizar cantidad y total del producto ya existente
            DetalleVenta detExistente = existente.get();
            detExistente.setCantidad(detExistente.getCantidad() + cantidad);
            // Calcular el nuevo total
            detExistente.setTotal(detExistente.getCantidad() * producto.getPrecioPublico());
        } else {
            // Crear un nuevo detalle
            DetalleVenta detalleVenta = new DetalleVenta(cantidad, producto);
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(cantidad);

            detalles.add(detalleVenta);
        }

        // Calcular el subtotal del carrito
        double sumaSubTotal = detalles.stream().mapToDouble(DetalleVenta::getTotal).sum();

        // Calcular el IVA de la compra
        double ivaComp = sumaSubTotal * 0.19;

        // Calcular el IVA de la compra
        double sumaTotal = sumaSubTotal + ivaComp;

        // Pasar datos al modelo
        model.addAttribute("carrito", detalles);
        model.addAttribute("sumaSubTotal", sumaSubTotal); // SubTotal acumulado del carrito
        model.addAttribute("ivaComp", ivaComp); // Iva de la compra del carrito
        model.addAttribute("sumaTotal", sumaTotal); // Total compra
        model.addAttribute("venta", venta);

        return "user/carrito";
    }

    @GetMapping("/delete/carrito/{codigo}")
    public String borrarProductoCarrito(@PathVariable Long codigo, Model model){

        List<DetalleVenta> detallesNueva = new ArrayList<DetalleVenta>();

        for (DetalleVenta detalleVenta : detalles) {
            if (!detalleVenta.getProducto().getCodigo().equals(codigo)) {
                detallesNueva.add(detalleVenta);
            }
        }

        detalles = detallesNueva;

        double sumaSubTotal = 0;
        // Calcular el total del carrito
        sumaSubTotal = detalles.stream().mapToDouble(DetalleVenta::getTotal).sum();

        // Calcular el IVA de la compra
        double ivaComp = sumaSubTotal * 0.19;

        // Calcular el IVA de la compra
        double sumaTotal = sumaSubTotal + ivaComp;

        // Pasar datos al modelo
        model.addAttribute("carrito", detalles);
        model.addAttribute("sumaSubTotal", sumaSubTotal); // SubTotal del carrito
        model.addAttribute("ivaComp", ivaComp); // Iva de la compra del carrito
        model.addAttribute("sumaTotal", sumaTotal); // Total compra
        model.addAttribute("venta", venta);

        return "user/carrito";
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {

        double sumaSubTotal = 0;
        // Calcular el total del carrito aunque este vacio
        sumaSubTotal = detalles.stream().mapToDouble(DetalleVenta::getTotal).sum();

        // Calcular el IVA de la compra
        double ivaComp = sumaSubTotal * 0.19;

        // Calcular el IVA de la compra
        double sumaTotal = sumaSubTotal + ivaComp;

        // Pasar datos al modelo
        model.addAttribute("carrito", detalles);
        model.addAttribute("sumaSubTotal", sumaSubTotal); // SubTotal del carrito
        model.addAttribute("ivaComp", ivaComp); // Iva de la compra del carrito
        model.addAttribute("sumaTotal", sumaTotal); // Total compra
        model.addAttribute("venta", venta);

        return "user/carrito";
    }
}
