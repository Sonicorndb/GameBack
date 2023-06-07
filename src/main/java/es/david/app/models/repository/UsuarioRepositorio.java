package es.david.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.app.models.entity.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	List<Usuario> findByEmailIs(String email);
}
