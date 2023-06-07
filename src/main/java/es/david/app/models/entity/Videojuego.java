package es.david.app.models.entity;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "VIDEOJUEGOS")
public class Videojuego {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "VIDEOJUEGO_ID")
	private Long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "PRECIO")
	private int precio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	public Videojuego() {
		super();
	}

	public Videojuego(Long id, String nombre, int precio, Usuario usuario){
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.usuario = usuario;
	}
	
	public Long getId() {
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

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videojuego other = (Videojuego) obj;
		return precio == other.precio && Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", usuario=" + usuario + "]";
	}

	
		
}

