package org.iesvdm.ventassringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFormDTO {
    private int id;

    private Double total;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    private int idCliente;

    private int idComercial;
}
