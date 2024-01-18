package org.iesvdm.ventassringboot.dao;

import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PedidoDAO<Pedido> extends RepositoryBase<Pedido> {
    public List<Pedido> getPedidosFromComercial(int idComercial);

    public Optional<Cliente> findClienteBy(int pedidoId);

    public Optional<Comercial> findComercialBy(int pedidoId);

}
