package org.iesvdm.ventassringboot.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    // @Min(value = )
    private int id;

    @NotBlank(message = "{nombre.error.blank}")
    @Size(max = 30, message = "{nombre.error.size")
    private String nombre;

    @NotBlank(message = "{ape1.error.blank}")
    @Size(max = 30, message = "{ape.error.size}")
    private String apellido1;

    private String apellido2;

    @NotBlank(message = "{ciudad.error.blank}")
    @Size(max = 50, message = "{ciudad.error.size}")
    private String ciudad;

    @NotNull(message = "")
    @Min(value = 100, message = "La categoría debe ser mayor a 100")
    @Max(value = 1000, message = "La categoría deber ser menor que 1000")
    private int categoria;

    @Email(message = "{error.email.cli} '${validatedValue}'", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
    private String email;
}
