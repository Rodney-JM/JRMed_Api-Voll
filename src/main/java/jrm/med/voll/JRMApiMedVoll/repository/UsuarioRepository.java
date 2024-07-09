package jrm.med.voll.JRMApiMedVoll.repository;

import jrm.med.voll.JRMApiMedVoll.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String username);
}
