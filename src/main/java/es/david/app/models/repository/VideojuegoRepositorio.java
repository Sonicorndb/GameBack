package es.david.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.app.models.entity.Videojuego;

@Repository
public interface VideojuegoRepositorio extends JpaRepository<Videojuego, Long> {

	public List<Videojuego> findByUsuario(Long usuarioId);
}
