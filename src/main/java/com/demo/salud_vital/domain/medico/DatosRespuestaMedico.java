package com.demo.salud_vital.domain.medico;

import com.demo.salud_vital.domain.direccion.DatosDireccion;


public record DatosRespuestaMedico(Long id, String nombre, String email, String telefono, String documento,
                                   DatosDireccion direccion) {
}
