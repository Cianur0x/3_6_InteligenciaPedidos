package org.iesvdm.ventassringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.ventassringboot.domain.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ComercialDTO {

    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private BigDecimal comision;
    private BigDecimal media;
    private BigDecimal total;
    private List<Pedido> pedidosOrdenados;
    private List<Map.Entry<Integer, Double>> totalCLientesLista;
}
