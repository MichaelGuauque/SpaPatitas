package com.spapatitas.controller;

import com.spapatitas.DTO.ProductoDTO;
import com.spapatitas.persistence.model.Categoria;
import com.spapatitas.service.interfaces.ICategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping()
    public String productos(Model model){
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "productos/vistaProductos";
    }

    @PostMapping("/crear")
    public String crear(ProductoDTO productoDTO){
        logger.info("Este es el producto {}", productoDTO);
        return "redirect:/productos";
    }
}
