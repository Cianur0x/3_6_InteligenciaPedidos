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
    @NotNull
    private int id;

    @NotBlank(message = "Por favor, introduzca nombre.")
    @Size(max = 30, message = "Nombre como máximo de  {max} caracteres.")
    private String nombre;

    @NotBlank(message = "Por favor, introduzca su primer apellido.")
    @Size(max = 30, message = "Apellido como máximo de {max} caracteres.")
    private String apellido1;

    private String apellido2;

    // poner  inclusive=true es reduntante, por defecto true
    @NotNull(message = "Por favor, introduzca una comisión")
    @DecimalMax(value = "0.946", message = "El valor tiene que ser menor que {value}")
    @DecimalMin(value = "0.276", message = "El valor tiene que ser mayor a {value}")
    private BigDecimal comision;
}
