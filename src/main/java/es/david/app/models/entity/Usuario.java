package es.david.app.models.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CONTRASENA")
	private String contrasena;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Videojuego> videojuegos = new HashSet<>();
	
	public Usuario() {
		super();
	}

	public Usuario(Long id, String email, String nombre, String contrasena) {
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public Set<Videojuego> getVideojuegos(){
		return videojuegos;
	}

	public void SetVideojuegos(Set<Videojuego> videojuegos) {
		this.videojuegos = videojuegos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(videojuegos, other.videojuegos) && Objects.equals(id, other.id)
				&& Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(contrasena, other.contrasena);
	}

	@Override
	public String toString() {
		return "[nombre=" + nombre + ", email=" + email + "]";
	}


}
