package com.spapatitas.controller;

import com.spapatitas.DTO.ProveedorDTO;
import com.spapatitas.persistence.model.Proveedor;
import com.spapatitas.service.interfaces.IProveedorService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProveedorController.class);

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping()
    public String proveedores(Model model){
        model.addAttribute("proveedores", proveedorService.findAll());
        return "proveedores/vistaProveedores";
    }

    @PostMapping("/create")
    public String create(ProveedorDTO proveedorDTO) throws Exception{
        // LOGGER.info("Objeto proveedor {}", proveedorDTO);
        proveedorService.save(proveedorDTO);
        return "redirect:/proveedores";
    }

    @GetMapping("/edit/{nit}")
    public String edit(@PathVariable Long nit, Model model){
        Proveedor proveedor = new Proveedor();
        Optional<Proveedor> optionalProveedor = proveedorService.findById(nit);
        proveedor = optionalProveedor.get();
        // LOGGER.info("Proveedor buscado: {}", proveedor);
        model.addAttribute("proveedor", proveedor);
        return "proveedores/vistaEditarProveedores";
    }

    @PostMapping("/update")
    public String update(Proveedor proveedor) throws IOException {
        proveedorService.update(proveedor);
        return "redirect:/proveedores";
    }
}
