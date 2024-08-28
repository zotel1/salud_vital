package com.demo.salud_vital.domain.consulta.validaciones;

import com.demo.salud_vital.domain.consulta.ConsultaRepository;
import com.demo.salud_vital.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos)  {
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario= datos.fecha().withHour(18);

        var pacienteConConsulta=repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("el paciente ya tiene una consulta para ese dia");
        }

    }
}
