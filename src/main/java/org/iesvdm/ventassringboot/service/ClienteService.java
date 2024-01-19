package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.ClienteDAO;
import org.iesvdm.ventassringboot.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ServiceBase<Cliente> {
    @Autowired
    private ClienteDAO<Cliente> clienteDAO;

    public List<Cliente> listAll() {
        return clienteDAO.getAll();
    }

    @Override
    public Cliente one(int id) {
        Optional<Cliente> optFab = clienteDAO.find(id);
        return optFab.orElse(null);
    }

    @Override
    public void create(Cliente cliente) {
        clienteDAO.create(cliente);
    }

    @Override
    public void replace(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    @Override
    public void delete(int id) {
        clienteDAO.delete(id);
    }

}
