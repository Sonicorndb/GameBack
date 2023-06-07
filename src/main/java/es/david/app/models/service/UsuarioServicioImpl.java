package es.david.app.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.app.dto.UsuarioDTO;
import es.david.app.exceptions.ResourcesNotFoundException;
import es.david.app.models.entity.Usuario;
import es.david.app.models.repository.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = mapearEntidad(usuarioDTO);
		Usuario nuevoUsuario = usuarioRepositorio.save(usuario);

		UsuarioDTO usuarioRespuesta = mapearDTO(nuevoUsuario);
		return usuarioRespuesta;
	}

	private UsuarioDTO mapearDTO(Usuario nuevoUsuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setId(nuevoUsuario.getId());
		usuarioDTO.setNombre(nuevoUsuario.getNombre());
		usuarioDTO.setEmail(nuevoUsuario.getEmail());
		usuarioDTO.setContrasena(nuevoUsuario.getContrasena());

		return usuarioDTO;
	}

	private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();

		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setContrasena(usuarioDTO.getContrasena());

		return usuario;
	}

	@Override
	public List<UsuarioDTO> obtenerTodosLosUsuarios() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		return usuarios.stream().map(usuario -> mapearDTO(usuario)).collect(Collectors.toList());
	}

	@Override
	public UsuarioDTO obtenerUsuarioPorId(long id) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Usuario", "id", id));
		return mapearDTO(usuario);
	}
	
	@Override
	public UsuarioDTO obtenerUsuarioPorEmail(String email) {
		List<Usuario> usuariosEmail = usuarioRepositorio.findByEmailIs(email);
		Usuario usuario = usuariosEmail.get(0);
		obtenerUsuarioPorId(usuario.getId());
		return mapearDTO(usuario);
	}


	@Override
	public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, long id) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Usuario", "id", id));
		
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setContrasena(usuarioDTO.getContrasena());
		
		Usuario usuarioActualizado = usuarioRepositorio.save(usuario);
		return mapearDTO(usuarioActualizado);
	}

	@Override
	public void eliminarUsuario(long id) {
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Persona", "id", id));
		usuarioRepositorio.delete(usuario);
	}
}