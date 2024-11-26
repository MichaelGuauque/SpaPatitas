package com.spapatitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @GetMapping("")
    public String show() {
        return "clientes/vistaClientes";
    }

}
