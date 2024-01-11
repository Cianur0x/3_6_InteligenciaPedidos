package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.ClienteDAO;
import org.iesvdm.ventassringboot.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteDAO clienteDAO;

    public List<Cliente> listAll() {

        return clienteDAO.getAll();
    }

    public Cliente one(Integer id) {
        Optional<Cliente> optFab = clienteDAO.find(id);
        return optFab.orElse(null);
    }

    public void newCliente(Cliente cliente) {

        clienteDAO.create(cliente);
    }

    public void replaceCliente(Cliente cliente) {

        clienteDAO.update(cliente);
    }

    public void deleteCliente(int id) {

        clienteDAO.delete(id);
    }

}
