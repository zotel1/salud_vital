package com.demo.salud_vital.domain.consulta.validaciones;

import com.demo.salud_vital.domain.consulta.DatosAgendarConsulta;
import com.demo.salud_vital.domain.medico.MedicoRepository;
import jakarta.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private MedicoRepository repository;

    public void validar(DatosAgendarConsulta datos) {
        if(datos.idMedico()==null){
            return;
        }
        var medicoActivo=repository.findActivoById(datos.idMedico());
        if(!medicoActivo){
            throw new ValidationException("No se puede permitir agendar citas con medicos inactivos en el sistema");
        }
    }
}
