package com.demo.salud_vital.domain.consulta;

import com.demo.salud_vital.domain.medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record DatosAgendarConsulta(

        @NotNull
        Long idPaciente,
        Long idMedico,
        String name,
        @NotNull
        @Future
        LocalDateTime fecha,
        Especialidad especialidad) {

}
