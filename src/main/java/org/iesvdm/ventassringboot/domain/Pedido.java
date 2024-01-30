package org.iesvdm.ventassringboot.domain;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private int id;

    private Double total;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    private int idCliente;

    private int idComercial;
    private Cliente cliente;
    private Comercial comercial;

    public Pedido(int id, Double total, LocalDate fecha, int idCliente, int idComercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idComercial = idComercial;
    }

    public Pedido(int id, Double total, LocalDate fecha, Cliente cliente, Comercial comercial, int idCliente, int idComercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
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
