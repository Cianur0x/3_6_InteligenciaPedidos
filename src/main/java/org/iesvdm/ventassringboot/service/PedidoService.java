package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.ClienteDAO;
import org.iesvdm.ventassringboot.dao.ComercialDAO;
import org.iesvdm.ventassringboot.dao.PedidoDAO;
import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoDAO<Pedido> pedidoDAO;
    @Autowired
    private ClienteDAO<Cliente> clienteDAO;
    @Autowired
    private ComercialDAO<Comercial> comercialDAO;


    public void newPedido(Pedido pedido) {
        pedidoDAO.create(pedido);
    }

    public List<Pedido> listAll() {
        return pedidoDAO.getAll();
    }

    public Pedido one(Integer id) {
        Optional<Pedido> pedido = pedidoDAO.find(id);
        Pedido peidd = null;
        if (pedido.isPresent()) {
            Pedido pedd = pedido.get();
            peidd = new Pedido(pedd.getId(), pedd.getTotal(), pedd.getFecha(), clienteDAO.find(pedd.getIdCliente()).orElse(null), comercialDAO.find(pedd.getIdComercial()).orElse(null), pedd.getIdCliente(), pedd.getIdComercial());

        }

        return peidd;
    }

    public void replacePedido(Pedido pedido) {
        pedidoDAO.update(pedido);
    }

    public void deletePedido(int id) {
        pedidoDAO.delete(id);
    }

    public List<Pedido> pedidosFromComercial(int id) {
        return pedidoDAO.getPedidosFromComercial(id);
    }

    public Cliente findClienteBy(int idPedido) {
        return pedidoDAO.findClienteBy(idPedido).orElse(null);
    }

    public Comercial findComercialBy(int idPedido) {
        return pedidoDAO.findComercialBy(idPedido).orElse(null);
    }

    public List<Comercial> listAllComerciales() {
        return comercialDAO.getAll();
    }

    public List<Cliente> listAllClientes() {
        return clienteDAO.getAll();
    }
}
