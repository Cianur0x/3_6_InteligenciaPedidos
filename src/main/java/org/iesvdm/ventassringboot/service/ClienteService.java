package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.ClienteDAO;
import org.iesvdm.ventassringboot.dao.ComercialDAO;
import org.iesvdm.ventassringboot.dao.PedidoDAO;
import org.iesvdm.ventassringboot.domain.Cliente;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService implements ServiceBase<Cliente> {
    @Autowired
    private ClienteDAO<Cliente> clienteDAO;

    @Autowired
    private ComercialDAO<Comercial> comercialDAO;

    @Autowired
    private PedidoDAO<Pedido> pedidoDAO;

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

    public List<Pedido> pedidosAsociados(int idCliente) {
        return pedidoDAO.getAll().stream().filter(pedido -> pedido.getIdCliente() == idCliente).toList();
    }

    public Map<Integer, Long> comercialesAsociados(int idCliente) {
        List<Pedido> pedidosAsociados = pedidosAsociados(idCliente);

        return pedidosAsociados.stream().collect(Collectors.groupingBy(Pedido::getIdComercial, Collectors.counting()));
    }

    public Long pedidosTrimestre(int idCliente) {
        List<Pedido> pedidosA = pedidosAsociados(idCliente);

        LocalDate hoy = LocalDate.now();
        Period periodo = Period.ofMonths(3);
        LocalDate trimestreAnterior = hoy.minus(periodo);
        // DateTimeFormatter f = DateTimeFormatter.ofPattern();

        return pedidosA.stream()
                .filter(pedido -> pedido.getFecha().isAfter(trimestreAnterior) && pedido.getFecha().isBefore(hoy)).count();
    }

    public Long pedidosSemestre (int idCliente) {
        List<Pedido> pedidosA = pedidosAsociados(idCliente);

        LocalDate hoy = LocalDate.now();
        Period periodo = Period.ofMonths(6);
        LocalDate semestreAnte = hoy.minus(periodo);
        // DateTimeFormatter f = DateTimeFormatter.ofPattern();

        return pedidosA.stream()
                .filter(pedido -> pedido.getFecha().isAfter(semestreAnte) && pedido.getFecha().isBefore(hoy)).count();
    }

    public Long pedidosAnio(int idCliente) {
        List<Pedido> pedidosA = pedidosAsociados(idCliente);

        LocalDate hoy = LocalDate.now();
        Period periodo = Period.ofYears(1);
        LocalDate minusYear = hoy.minus(periodo);
        // DateTimeFormatter f = DateTimeFormatter.ofPattern();

        return pedidosA.stream()
                .filter(pedido -> pedido.getFecha().isAfter(minusYear) && pedido.getFecha().isBefore(hoy)).count();
    }
    public Long pedidosLustro(int idCliente) {
        List<Pedido> pedidosA = pedidosAsociados(idCliente);

        LocalDate hoy = LocalDate.now();
        Period periodo = Period.ofYears(5);
        LocalDate minusYear = hoy.minus(periodo);
        // DateTimeFormatter f = DateTimeFormatter.ofPattern();

        return pedidosA.stream()
                .filter(pedido -> pedido.getFecha().isAfter(minusYear) && pedido.getFecha().isBefore(hoy)).count();
    }

}
