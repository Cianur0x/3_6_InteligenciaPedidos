package org.iesvdm.ventassringboot.dto;

public class ClienteDTO {
    private int idCliente;
    private double totalPedidos;

    public ClienteDTO(int idCliente, double totalPedidos) {
        this.idCliente = idCliente;
        this.totalPedidos = totalPedidos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getTotalPedidos() {
        return totalPedidos;
    }
}

