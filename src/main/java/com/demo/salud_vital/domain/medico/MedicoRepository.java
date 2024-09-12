package com.demo.salud_vital.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);


    // @Query("""
    //     select m from Medico m
    //     where m.activo= 1
    //    and m.especialidad = :especialidad
    //    and m.id not in (
    //       select c.medico.id from Consulta c
    //       where c.fecha = :fecha
    //   )
    //    order by function('rand')
    //    """)
    // Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);


    @Query("""
            select m.activo 
            from Medico m
            where m.id=:idMedico
            """)
    Boolean findActivoById(Long idMedico);

    @Query("""
            select m
            from Medico m
            where m.especialidad=:especialidad
            """)
    List<Medico> findByEspecialidad(Especialidad especialidad);

    Optional<Medico> findById(Long id);
}