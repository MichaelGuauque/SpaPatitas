package com.spapatitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promociones")
public class PromocionesController {

    @GetMapping()
    public String show(){
        return "promociones/promocionesConstruccion";
    }
}
