package com.spapatitas.service;

import com.spapatitas.persistence.model.*;
import com.spapatitas.persistence.repository.ClienteRepository;
import com.spapatitas.service.implementation.ClienteService;
import com.spapatitas.service.interfaces.IClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private IClienteService clienteServicio;

//    public ClienteServiceTest() {
//        this.clienteServicio = new ClienteService();
//
//    }

//    @Test
//    void save() {
//        UserEntity usuarioEjemplo = new UserEntity(null, "usuario123", "contraseñaSegura123", true, true, true, true, null, null);
//        Cliente cliente = new Cliente();
//        cliente.setPrimerNombre("julanito");
//        cliente.setSegundoNombre("perez");
//        cliente.setPrimerApellido("guake");
//        cliente.setSegundoApellido("fige");
//        cliente.setCedula(165485);
//        cliente.setDireccion("avenida pta mrda");
//        cliente.setEstado(true);
//        cliente.setFechaNacimiento(LocalDate.of(1990, 5, 15));
//        cliente.setMascotas(new ArrayList<Mascota>());
//        cliente.setTelefono(316589452);
//        cliente.setGenero(Genero.MASCULINO);
//        cliente.setCitas(new ArrayList<Cita>());
//        cliente.setUsuario(usuarioEjemplo);
//
//        clienteServicio.save(cliente);
//        assertTrue(true);
//    }

//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void findByCedula() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void deshabilitar() {
//    }
//
//    @Test
//    void habilitar() {
//    }
}