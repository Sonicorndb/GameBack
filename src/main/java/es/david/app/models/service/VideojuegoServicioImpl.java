package es.david.app.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.app.dto.VideojuegoDTO;
import es.david.app.exceptions.ResourcesNotFoundException;
import es.david.app.models.entity.Videojuego;
import es.david.app.models.entity.Usuario;
import es.david.app.models.repository.VideojuegoRepositorio;
import es.david.app.models.repository.UsuarioRepositorio;

@Service
public class VideojuegoServicioImpl implements VideojuegoServicio {

	@Autowired
	private VideojuegoRepositorio VideojuegoRepositorio;

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public VideojuegoDTO crearVideojuego(Long usuarioId, VideojuegoDTO videojuegoDTO) {
		Videojuego videojuego = mapearEntidad(videojuegoDTO,usuarioId);
		Usuario usuario = usuarioRepositorio.findById(usuarioId)
				.orElseThrow(() -> new ResourcesNotFoundException("Usuario", "id", usuarioId));

		videojuego.setUsuario(usuario);
		Videojuego nuevoVideojuego = VideojuegoRepositorio.save(videojuego);
		return mapearDTO(nuevoVideojuego);
	}

	private VideojuegoDTO mapearDTO(Videojuego videojuego) {
		VideojuegoDTO VideojuegoDTO = new VideojuegoDTO();

		VideojuegoDTO.setId(videojuego.getId());
		VideojuegoDTO.setNombre(videojuego.getNombre());
		VideojuegoDTO.setPrecio(videojuego.getPrecio());

		return VideojuegoDTO;
	}

	private Videojuego mapearEntidad(VideojuegoDTO videojuegoDTO, Long usuarioId) {
		Videojuego videojuego = new Videojuego();

		videojuego.setNombre(videojuegoDTO.getNombre());
		videojuego.setPrecio(videojuegoDTO.getPrecio());
		videojuego.setUsuario(usuarioRepositorio.findById(usuarioId).get());

		return videojuego;
	}

	@Override
	public List<VideojuegoDTO> obtenerVideojuegosPorUsuarioId(Long usuarioId) {
		List<Videojuego> videojuegos = VideojuegoRepositorio.findByUsuario(usuarioId);
		return videojuegos.stream().map(videojuego -> mapearDTO(videojuego)).collect(Collectors.toList());
	}

	@Override
	public List<VideojuegoDTO> obtenerTodosLosVideojuegos() {
		List<Videojuego> videojuegos = VideojuegoRepositorio.findAll();
		return videojuegos.stream().map(videojuego -> mapearDTO(videojuego)).collect(Collectors.toList());
	}

	@Override
	public VideojuegoDTO obtenerVideojuegoPorId(Long videojuegoId) {
		Videojuego videojuego = VideojuegoRepositorio.findById(videojuegoId)
				.orElseThrow(() -> new ResourcesNotFoundException("Videojuego", "id", videojuegoId));

		return mapearDTO(videojuego);
	}
}
