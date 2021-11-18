package es.unican.ps.supermercado.negocio.dominio;

import java.util.HashSet;
import java.util.Set;

public class Usuario {

	private String nombre;
	private String dni;
	private String direccion;
	private String email;
	private int comprasMensuales;
	private Set<LineaPedido> carritoActual = new HashSet<LineaPedido>();
	private Set<Pedido> pedidos = new HashSet<Pedido>();
	
	public Usuario(String nombre, String dni, String direccion, String email) {
		this.setNombre(nombre);
		this.setDni(dni);
		this.setDireccion(direccion);
		this.setEmail(email);
		this.setComprasMensuales(0);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getComprasMensuales() {
		return comprasMensuales;
	}

	public void setComprasMensuales(int comprasMensuales) {
		this.comprasMensuales = comprasMensuales;
	}

	public Set<LineaPedido> getCarritoActual() {
		return carritoActual;
	}

	public void setCarritoActual(Set<LineaPedido> carritoActual) {
		this.carritoActual = carritoActual;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
