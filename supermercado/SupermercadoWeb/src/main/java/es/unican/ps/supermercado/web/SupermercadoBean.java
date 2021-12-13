package es.unican.ps.supermercado.web;

import java.sql.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.dominio.LineaPedido;
import es.unican.ps.supermercado.common.dominio.Usuario;
import es.unican.ps.supermercado.common.interfaces.IGestionUsuariosRemote;
import es.unican.ps.supermercado.common.interfaces.IRealizacionPedidosRemote;

@Named
@RequestScoped
public class SupermercadoBean {
	
	@EJB
	private IRealizacionPedidosRemote myGestionPedidos;
	
	@EJB
	private IGestionUsuariosRemote myGestionUsuarios;
	
	private String dni;
	private Usuario usuario;
	private int unidades;
	private Date horaRecogida;
	private Articulo articulo;
	private Set<Articulo> articulos;
	private Set<LineaPedido> carrito;
	private double precioTotal;
	private long referencia;
	
	public SupermercadoBean() {	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Date getHoraRecogida() {
		return horaRecogida;
	}

	public void setHoraRecogida(Date horaRecogida) {
		this.horaRecogida = horaRecogida;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	public Set<LineaPedido> getCarrito() {
		return carrito;
	}

	public void setCarrito(Set<LineaPedido> carrito) {
		this.carrito = carrito;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public long getReferencia() {
		return referencia;
	}

	public void setReferencia(long referencia) {
		this.referencia = referencia;
	}
	
	public String login() {
		usuario = myGestionUsuarios.login(dni);
		return "listaArticulos";
	}
	
	public String registrarse() {
		myGestionUsuarios.registrar(null);
		return "listaArticulos";
	}
	
	public String verCarrito() {
		myGestionPedidos.verCarrito(usuario);
		return "carrito";
	}
	
	public String anhadirAlCarrito() {
		myGestionPedidos.anhadirAlCarrito(articulo.getId(), unidades, usuario);
		return "articulo";
	}
	
	public String inicio() {
		return "listaArticulos";
	}

}
