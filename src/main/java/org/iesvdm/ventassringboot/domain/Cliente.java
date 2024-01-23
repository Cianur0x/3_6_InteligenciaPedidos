package org.iesvdm.ventassringboot.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @NotNull
    private int id;

    @NotBlank(message = "Por favor, introduzca un nombre.")
    @Size(max = 30, message = "Nombre como máximo de 30 caracteres.")
    private String nombre;

    @NotBlank(message = "Por favor, introduzca su primer apellido.")
    @Size(max = 30, message = "Apellido como máximo de 30 caracteres.")
    private String apellido1;

    private String apellido2;

    @NotBlank(message = "Por favor, introduzca una ciudad.")
    @Size(max = 50, message = "Ciudad máximo de 50 caracteres.")
    private String ciudad;

    @NotNull(message = "Por favor, introduzca una categoría.")
    @Min(value = 100, message = "La categoría debe ser mayor a 100")
    @Max(value = 1000, message = "La categoría deber ser menor que 1000")
    private int categoria;
}
