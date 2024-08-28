package com.demo.salud_vital.domain.medico;

import com.demo.salud_vital.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


public record DatosRegistroMedico(

        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 0, max = 15)
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {
}
