package com.demo.salud_vital.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);


    @Query("""
            select * from Medico m
            where m.activo = 1 
            and
            m.especialidad_id=:especialidad 
            and
            m.id not in(  
                select c.medico_id from Consulta c
                where
                c.fecha=:fecha
            )
            order by rand()
            limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(@Param("especialidadId") Long especialidad, @Param("fecha") LocalDateTime fecha);
//Medico seleccionarMedicoConEspecialidadEnFecha( Especialidad especialidad, LocalDateTime fecha);
//
//    Page<Medico> seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);


    @Query("""
            select m.activo 
            from Medico m
            where m.id=:idMedico
            """)
    Boolean findActivoById(Long idMedico);
}
