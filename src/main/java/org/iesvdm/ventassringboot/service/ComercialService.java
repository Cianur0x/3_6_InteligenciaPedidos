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

    public BigDecimal totalPedidosComercial(List<Pedido> pedidos) {
        return BigDecimal.valueOf(pedidos.stream().mapToDouble(Pedido::getTotal).sum());
    }

    /**
     * TODO Añade las estadísticas de total y media de pedidos del comercial en su detalle.
     *  Utiliza un DTO para transferir a la vista las estadísticas de inteligencia de pedidos
     */
    public BigDecimal mediaPedidosComercial(List<Pedido> pedidos) {
        // bd = bd.setScale(2, RoundingMode.HALF_UP);
        return totalPedidosComercial(pedidos).divide(BigDecimal.valueOf(pedidos.size()), 2, RoundingMode.HALF_UP);
    }

    public List<Pedido> pedidosSortedByTotal(List<Pedido> pedidos) {
        return pedidos.stream().sorted(Comparator.comparing(Pedido::getTotal)).collect(Collectors.toList());
    }

    // List<Map.Entry<Integer, Double>>
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
