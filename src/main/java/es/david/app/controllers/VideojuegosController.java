package es.david.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.app.dto.VideojuegoDTO;
import es.david.app.models.service.VideojuegoServicio;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class VideojuegosController {

	@Autowired
	private VideojuegoServicio videojuegoServicio;
	
	@GetMapping("/videojuegos")
	public List<VideojuegoDTO> listarVideojuegos() {
		return videojuegoServicio.obtenerTodosLosVideojuegos();
	}
	
	@GetMapping("/usuarios/{usuarioId}/videojuegos/")
	public List<VideojuegoDTO> obtenerVideojuegoUsuarioPorId(@PathVariable(value = "usuarioId") Long usuarioId, @PathVariable(value = "id") Long videojuegoId) {
		return videojuegoServicio.obtenerVideojuegosPorUsuarioId(usuarioId);
	}
	
	@GetMapping("/videojuegos/{id}")
	public ResponseEntity<VideojuegoDTO> obtenerVideojuegoPorId(@PathVariable(value = "id") Long videojuegoId) {
		VideojuegoDTO videojuegoDTO = videojuegoServicio.obtenerVideojuegoPorId( videojuegoId);
		return new ResponseEntity<>(videojuegoDTO, HttpStatus.OK);
	}
	
	@PostMapping("/usuarios/{usuarioId}/videojuegos")
	public ResponseEntity<VideojuegoDTO> guardarVideojuego(@PathVariable(value = "usuarioId") Long usuarioId, @RequestBody VideojuegoDTO videojuegoDTO) {
		return new ResponseEntity<>(videojuegoServicio.crearVideojuego(usuarioId, videojuegoDTO), HttpStatus.CREATED);
	}
}
