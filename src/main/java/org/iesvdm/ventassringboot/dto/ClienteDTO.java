package org.iesvdm.ventassringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private int categoria;
    private String email;
    // map para conteo de pedidos segun comercial
    // private List<Comercial> comercialesAsociados;
    // numero de pedidos realizados en el trimestre, semestre, año y lustro = 5años
    private Map<Integer, Long> comercialesAsociados;
    private Long pedidosTrimestre;
    private Long pedidosSemestre;
    private Long pedidosAnio;
    private Long pedidosLustro;


}

