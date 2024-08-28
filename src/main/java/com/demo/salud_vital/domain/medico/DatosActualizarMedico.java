package com.demo.salud_vital.domain.medico;

import com.demo.salud_vital.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;


public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, DatosDireccion direccion) {
}
