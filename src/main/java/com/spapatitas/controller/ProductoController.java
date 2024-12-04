package com.spapatitas.controller;

import com.spapatitas.DTO.ProductoDTO;
import com.spapatitas.persistence.model.Categoria;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.TipoServicio;
import com.spapatitas.service.implementation.UploadFileService;
import com.spapatitas.service.interfaces.ICategoriaService;
import com.spapatitas.service.interfaces.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ICategoriaService categoriaService;
    @Autowired
    private IProductoService productoService;
    @Autowired
    private UploadFileService upload;

    @GetMapping()
    public String productos(Model model){
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categorias);
        return "productos/vistaProductos";
    }

    @PostMapping("/crear")
    public String crear(ProductoDTO productoDTO,@RequestParam("imagenFile") MultipartFile file) throws Exception {

        String nombreImagen = upload.saveImages(file);
        productoDTO.setImagen(nombreImagen);

//        logger.info("Este es el producto {}", productoDTO);
        productoService.save(productoDTO);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{codigo}")
    public String editar(@PathVariable Long codigo, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.findById(codigo);
        producto = optionalProducto.get();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.findAll());
        return "productos/vistaEditarProductos";
    }

    @PostMapping("/actualizar")
    public String actualizar(Producto producto, @RequestParam("imagenFile") MultipartFile file) throws IOException {

        if(file.isEmpty()){
            Producto p = new Producto();
            p = productoService.findById(producto.getCodigo()).get();
            producto.setImagen(p.getImagen());
        }else{
            String nombreImagen = upload.saveImages(file);
            producto.setImagen(nombreImagen);
        }
//        logger.info("Este es el producto {}", producto);
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/search")
    public String buscarProducto(@RequestParam String nombre, Model model) {
        // Filtrar los servicios que coincidan con el nombre
        List<Producto> productosEncontrados = productoService.findByNombre(nombre);
        model.addAttribute("productos", productosEncontrados);
        return "productos/vistaProductosUsuario"; // O la vista donde quieres mostrar los resultados
    }



}
