package jrm.med.voll.JRMApiMedVoll.repository;

import jrm.med.voll.JRMApiMedVoll.models.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable page);

    @Query("""
            SELECT p.ativo
            FROM Paciente p
            WHERE
            p.id = :pacienteId
            """)
    boolean findAtivoById(Long pacienteId);
}
