package es.unican.ps.supermercado.negocio.interfaces;

import java.util.Date;
import java.util.Set;

import es.unican.ps.supermercado.negocio.dominio.Articulo;
import es.unican.ps.supermercado.negocio.dominio.LineaPedido;
import es.unican.ps.supermercado.negocio.dominio.Pedido;

public interface IRealizacionPedidos {

	public Set<Articulo> verArticulos();
	public boolean anhadirAlCarrito(String refArticulo, int numUnidades, Set<LineaPedido> carrito);
	public Set<LineaPedido> verCarrito();
	public Pedido confirmarPedido(Date horaDeRecogida, Set<LineaPedido> carrito);
	
}
