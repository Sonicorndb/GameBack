package es.david.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.app.dto.UsuarioDTO;
import es.david.app.models.service.UsuarioServicio;

@RestController
@RequestMapping("/api/")
@CrossOrigin()
public class UsuarioController {
	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("/usuarios")
	public List<UsuarioDTO> listarUsuarios() {
		return usuarioServicio.obtenerTodosLosUsuarios();
	}

	@GetMapping("usuarios/id/{id}")
	public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable long id) {
		return ResponseEntity.ok(usuarioServicio.obtenerUsuarioPorId(id));
	}
	
	@GetMapping("usuarios/email/{email}")
	public ResponseEntity<UsuarioDTO> obtenerUsuarioPorEmail(@PathVariable String email) {
		return ResponseEntity.ok(usuarioServicio.obtenerUsuarioPorEmail(email));
	}

	@PostMapping("/usuarios")
	public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		return new ResponseEntity<>(usuarioServicio.crearUsuario(usuarioDTO), HttpStatus.CREATED);
	}

	@PutMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO,
			@PathVariable(name = "id") long id){
		UsuarioDTO usuarioRespuesta = usuarioServicio.actualizarUsuario(usuarioDTO, id);
		return new ResponseEntity<>(usuarioRespuesta, HttpStatus.OK);
	}

	@DeleteMapping("usuarios/eliminar/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable(name = "id") long id) {
		usuarioServicio.eliminarUsuario(id);
		return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
	}
}
