package es.unican.ps.supermercado.negocio.dominio;


public class Usuario {

	private String nombre;
	private String dni;
	private String direccion;
	private String email;
	private int comprasMensuales;
	
	public Usuario(String nombre, String dni, String direccion, String email) {
		this.nombre = nombre;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
	}
	
	// TODO: hacer get y set
	public String getNombre() {
		return nombre;
	}
}
