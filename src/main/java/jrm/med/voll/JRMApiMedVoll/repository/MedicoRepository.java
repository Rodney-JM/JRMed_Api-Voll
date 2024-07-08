package jrm.med.voll.JRMApiMedVoll.repository;

import jrm.med.voll.JRMApiMedVoll.models.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable page);
}
