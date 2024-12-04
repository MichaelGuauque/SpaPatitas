package com.spapatitas.controller;

import com.spapatitas.DTO.PromocionDTO;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.PromocionProducto;
import com.spapatitas.persistence.model.TipoServicio;
import com.spapatitas.service.interfaces.IProductoService;
import com.spapatitas.service.interfaces.IPromocionProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/promociones")
public class PromocionesController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IPromocionProductoService promocionService;
    @Autowired
    private IProductoService productoService;

    @GetMapping()
    public String show(Model model){
        model.addAttribute("promociones", promocionService.findAll());
        return "promociones/vistaPromociones";
    }

    @GetMapping("/buscar")
    public String buscarProducto(@RequestParam("codigo") Long codigo, Model model){
        model.addAttribute("promociones", promocionService.findAll());
        Optional<Producto> optionalProducto = productoService.findById(codigo);
        if(optionalProducto.isPresent()){
            Producto producto = optionalProducto.get();
            model.addAttribute("producto", producto);
        }else {
            model.addAttribute("producto", new Producto());
        }
        return "promociones/vistaPromociones";
    }

    @PostMapping("/guardar")
    public String guardar(PromocionDTO promocionDTO){
//        logger.info("esta es la promo: {}", promocionDTO);
        promocionService.save(promocionService.cambiarDTO(promocionDTO));
        return"redirect:/promociones";
    }

    @PostMapping("/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        Optional<PromocionProducto> promocion = promocionService.findById(id);
        if (promocion.isPresent()) {
            if (promocion.get().isEstado()) {
                promocionService.deshabilitar(id);
            } else {
                promocionService.habilitar(id);
            }
        }
        return "redirect:/promociones";
    }
}
