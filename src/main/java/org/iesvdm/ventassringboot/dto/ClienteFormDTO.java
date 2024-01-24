package org.iesvdm.ventassringboot.dto;

public class ClienteFormDTO {
    private int idCliente;
    private double totalPedidos;

    public ClienteFormDTO(int idCliente, double totalPedidos) {
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

