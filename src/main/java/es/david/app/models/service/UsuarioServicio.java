package es.david.app.models.service;

import java.util.List;

import es.david.app.dto.UsuarioDTO;

public interface UsuarioServicio {

	public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
	
	public List<UsuarioDTO> obtenerTodosLosUsuarios();
	
	public UsuarioDTO obtenerUsuarioPorId(long id);
	
	public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO, long id);
	
	public void eliminarUsuario(long id);

	public UsuarioDTO obtenerUsuarioPorEmail(String email);
}
