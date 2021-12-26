package es.unican.ps.supermercado.common.dominio;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Usuarios")
public class Usuario implements Serializable {

	@Id
	private String dni;
	
	private String nombre;
	
	private String direccion;
	
	private String email;
	
	@Column(name="Compras_Mensuales")
	private int comprasMensuales;
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private List<LineaPedido> carritoActual = new LinkedList<LineaPedido>();
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private List<Pedido> pedidos = new LinkedList<Pedido>();
	
	public Usuario() {
		
	}
	
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

	public List<LineaPedido> getCarritoActual() {
		return carritoActual;
	}

	public void setCarritoActual(List<LineaPedido> carritoActual) {
		this.carritoActual = carritoActual;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void anhadirPedido(Pedido p) {
		pedidos.add(p);
		comprasMensuales++;
	}
	
}
