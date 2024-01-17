package org.iesvdm.ventassringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Pedido {
    private int id;
    private Double total;
    private LocalDate fecha;
    private int idCliente;
    private int idComercial;
    private Cliente cliente;
    private Comercial comercial;

    public Pedido() {

    }

    public Pedido(int id, Double total, LocalDate fecha, int idCliente, int idComercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idComercial = idComercial;
    }

    public Pedido(int id, Double total, LocalDate fecha, Cliente cliente, Comercial comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
    }
}
