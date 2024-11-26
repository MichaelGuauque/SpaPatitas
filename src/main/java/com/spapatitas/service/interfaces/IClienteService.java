package com.spapatitas.service.interfaces;

import com.spapatitas.DTO.ClienteDTO;
import com.spapatitas.persistence.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    // Encuentra todos los clientes
    List<Cliente> findAll();

    // Encuentra un cliente por ID
    Optional<Cliente> findById(Long idCliente);

    // Encuentra un cliente por su cédula
    Optional<Cliente> findByCedula(int cedula);

    // Guarda un nuevo cliente
    Cliente save(ClienteDTO cliente);

    // Actualiza un cliente existente
    Cliente update(Cliente cliente);

    // Deshabilita un cliente (cambia el estado a false)
    void deshabilitar(Long idCliente);

    // Habilita un cliente (cambia el estado a true)
    void habilitar(Long idCliente);
}
