package org.iesvdm.ventassringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private String categoria;

    public Cliente() {

    }
}
