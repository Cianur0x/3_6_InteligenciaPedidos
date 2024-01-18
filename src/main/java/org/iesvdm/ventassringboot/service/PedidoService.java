package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.PedidoDAO;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoDAO pedidoDAO;

    public PedidoService(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public List<Pedido> listAll() {
        return pedidoDAO.getAll();
    }

//    public Pedido one(Integer id) {
//        Optional<Pedido> optFab = pedidoDAO.find(id);
//        return optFab.orElse(null);
//    }

}
