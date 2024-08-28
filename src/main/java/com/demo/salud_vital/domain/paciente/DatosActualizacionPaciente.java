package com.demo.salud_vital.domain.paciente;

import com.demo.salud_vital.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;


public record DatosActualizacionPaciente(
        @NotNull
        Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion) {
}
