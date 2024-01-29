package org.iesvdm.ventassringboot.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {

    private int id;

    @NotBlank(message = "{nombre.error.blank}")
    @Size(max = 30, message = "{nombre.error.size}")
    private String nombre;

    @NotBlank(message = "{ape1.error.blank}")
    @Size(max = 30, message = "{ape1.error.size}")
    private String apellido1;

    private String apellido2;

    // poner  inclusive=true es reduntante, por defecto true
    @NotNull(message = "{comision.error.null}")
    @DecimalMax(value = "0.946", message = "{comision.error.max}")
    @DecimalMin(value = "0.276", message = "{comision.error.min}")
    private BigDecimal comision;
}
