package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.ComercialDAO;
import org.iesvdm.ventassringboot.dao.PedidoDAO;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {
    private final ComercialDAO comercialDAO;
    private final PedidoDAO pedidoDAO;

    public ComercialService(ComercialDAO comercialDAO, PedidoDAO pedidoDAO) {
        this.comercialDAO = comercialDAO;
        this.pedidoDAO = pedidoDAO;
    }

    public List<Comercial> listAll() {
        return comercialDAO.getAll();
    }

    public Comercial one(Integer id) {
        Optional<Comercial> optFab = comercialDAO.find(id);
        return optFab.orElse(null);
    }

    public List<Pedido> pedidosFromCOmercial(int id) {

        return null;
    }

    public void newComercial(Comercial cliente) {
        comercialDAO.create(cliente);
    }

    public void replaceComercial(Comercial cliente) {
        comercialDAO.update(cliente);
    }

    public void deleteComercial(int id) {
        comercialDAO.delete(id);
    }

}
