package com.demo.salud_vital.domain.consulta.validaciones;

import com.demo.salud_vital.domain.consulta.DatosAgendarConsulta;
import com.demo.salud_vital.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{
    @Autowired
    private PacienteRepository repository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idPaciente()==null){
            return;
        }
        var pacienteActivo=repository.findActivoById(datos.idPaciente());

        if(!pacienteActivo){
            throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
        }
    }
}
