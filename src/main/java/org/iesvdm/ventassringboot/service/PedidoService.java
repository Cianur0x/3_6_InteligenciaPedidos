package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.PedidoDAO;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoDAO<Pedido> pedidoDAO;

    public List<Pedido> listAll() {
        return pedidoDAO.getAll();
    }

}
