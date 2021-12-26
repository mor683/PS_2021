package es.unican.ps.supermercado.web;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.dominio.LineaPedido;
import es.unican.ps.supermercado.common.dominio.Pedido;
import es.unican.ps.supermercado.common.dominio.Usuario;
import es.unican.ps.supermercado.common.interfaces.IGestionUsuariosRemote;
import es.unican.ps.supermercado.common.interfaces.IRealizacionPedidosRemote;

@Named
@ApplicationScoped
public class SupermercadoBean {
	
	@EJB
	private IRealizacionPedidosRemote myGestionPedidos;
	
	@EJB
	private IGestionUsuariosRemote myGestionUsuarios;
	
	private String dni;					//
	private Usuario usuario;			//
	private int unidades;				//
	private Date horaRecogida;			//
	private Articulo articulo;			//
	private String nombreArticulo;
	private List<Articulo> articulos;	//
	private List<LineaPedido> carrito;	//
	private double precioTotal;			//
	private Pedido pedido;				//
	private String referencia;
	
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

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	public List<LineaPedido> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<LineaPedido> carrito) {
		this.carrito = carrito;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}
	
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String login() {
		this.usuario = myGestionUsuarios.login(dni);
		this.carrito = myGestionPedidos.verCarrito(usuario);
		this.articulos = myGestionPedidos.verArticulos();
		return "listaArticulos";
	}
	
	public String registrarse() {
		//myGestionUsuarios.registrar(null);
		this.articulos = myGestionPedidos.verArticulos();
		return "listaArticulos";
	}
	
	public String verCarrito() {
		this.precioTotal = 0;
		for(LineaPedido l : carrito) {
			this.precioTotal += l.getCantidad() * l.getArticulo().getPrecio();
		}
		return "carrito";
	}
	
	public String introducirCantidad(Articulo articulo) {
		this.setArticulo(articulo);
		this.nombreArticulo = articulo.getNombre();
		return "articulo";
	}
	
	public String anhadirAlCarrito() {
		// hacer que añadir al carrito devuelva el carrito directamente
		boolean result = myGestionPedidos.anhadirAlCarrito(articulo.getId(), unidades, usuario);
		if (result) {
			this.carrito.add(new LineaPedido(unidades, articulo));
			this.unidades = 0;
		} else {
			this.unidades = 9999;
		}
		return "";
	}
	
	public String inicio() {
		return "listaArticulos";
	}
	
	public String confirmarPedido() {
		this.pedido = myGestionPedidos.confirmarPedido(horaRecogida, usuario);
		
		if (pedido == null) {
			this.setReferencia("ERROR: Hora de recogida incorrecta");
		} else {
			this.setReferencia(this.pedido.getReferencia());
			this.carrito.clear();
		}

		return "pedidoRealizado";
	}
}
