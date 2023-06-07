package es.david.app.models.service;

import java.util.List;

import es.david.app.dto.VideojuegoDTO;

public interface VideojuegoServicio {

	public VideojuegoDTO crearVideojuego(Long usuarioId, VideojuegoDTO videojuegoDTO);
	
	public List<VideojuegoDTO> obtenerVideojuegosPorUsuarioId(Long usuarioId);
	
	public List<VideojuegoDTO> obtenerTodosLosVideojuegos();
	
	public VideojuegoDTO obtenerVideojuegoPorId(Long videojuegoId);
}
