package org.iesvdm.ventassringboot.service;

import org.iesvdm.ventassringboot.dao.ComercialDAO;
import org.iesvdm.ventassringboot.dao.PedidoDAO;
import org.iesvdm.ventassringboot.domain.Comercial;
import org.iesvdm.ventassringboot.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComercialService {
    @Autowired
    private ComercialDAO<Comercial> comercialDAO;

    @Autowired
    private PedidoDAO<Pedido> pedidoDAO;

    public List<Comercial> listAll() {
        return comercialDAO.getAll();
    }

    public Comercial one(Integer id) {
        Optional<Comercial> optFab = comercialDAO.find(id);
        return optFab.orElse(null);
    }

    public List<Pedido> pedidosFromComercial(int id) {
        return pedidoDAO.getPedidosFromComercial(id);
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

    /**
     * Calcular la suma total de los pedidos
     *
     * @param pedidos lista de pedidos de la que se suma
     * @return total de la suma
     */
    public BigDecimal totalPedidosComercial(List<Pedido> pedidos) {
        return BigDecimal.valueOf(pedidos.stream().mapToDouble(Pedido::getTotal).sum());
    }

    /**
     * Calcular la media del total de pedidos
     *
     * @param pedidos la lista de pedidos
     * @return media, redondeado a dos decimales
     */
    public BigDecimal mediaPedidosComercial(List<Pedido> pedidos) {
        // bd = bd.setScale(2, RoundingMode.HALF_UP);
        return totalPedidosComercial(pedidos).divide(BigDecimal.valueOf(pedidos.size()), 2, RoundingMode.HALF_UP);
    }

    /**
     * Lista de pedidos ordenada según total
     *
     * @param pedidos, lista que se va a ordenar
     * @return lista pedidos ordenada
     */
    public List<Pedido> pedidosSortedByTotal(List<Pedido> pedidos) {
        return pedidos.stream().sorted(Comparator.comparing(Pedido::getTotal)).collect(Collectors.toList());
    }

    /**
     * Lista ordenada de clientes, con el total de pedidos, y su id.
     *
     * @param pedidos lisa de la que e filtra
     * @return lista con id y total de pedidos por cliente
     */
    public List<Map.Entry<Integer, Double>> totalClienteSorted(List<Pedido> pedidos) {
        // listado de clientes ordenados por cuantía de pedido de mayor a menor. El listado iría a continuación del listado de pedidos
        // Lista de clientes con el total de pedidos
        Map<Integer, Double> idCLiente = pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getIdCliente, Collectors.summingDouble(Pedido::getTotal)));

        // Ordenar el mapa por valores (totales) de mayor a menor
        var sortedTotales = idCLiente.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .toList();

        return sortedTotales;
    }
}
