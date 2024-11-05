package com.spapatitas.service.implementation;

import com.spapatitas.persistence.model.Cliente;
import com.spapatitas.persistence.repository.ClienteRepository;
import com.spapatitas.service.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    //Implementacion de Metodos

    //Encuentra todos los clientes
    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    //Encuentra un cliente por su ID
    @Override
    public Optional<Cliente> findById(Long idCliente) {
        return clienteRepository.findById(idCliente);
    }

    //Encuentra un cliente por su cedula
    @Override
    public Optional<Cliente> findByCedula(int cedula) {
        return clienteRepository.findClienteByCedula(cedula);
    }

    //Guarda un cliente
    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    //Actualiza un cliente existente
    @Override
    public Cliente update(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getIdCliente())) {
            return clienteRepository.save(cliente);
        }
        throw new IllegalArgumentException("El cliente con ID " + cliente.getIdCliente() + " no existe.");
    }

    //Deshabilita un cliente (cambia el estado a false)
    @Override
    public void deshabilitar(Long idCliente) {
        Optional<Cliente> cliente = findById(idCliente);
        cliente.ifPresent(c -> {
            c.setEstado(false);
            clienteRepository.save(c);
        });
    }

    // Habilita un cliente (cambia el estado a true)
    @Override
    public void habilitar(Long idCliente) {
        Optional<Cliente> cliente = findById(idCliente);
        cliente.ifPresent(c -> {
            c.setEstado(true);
            clienteRepository.save(c);
        });
    }


}
