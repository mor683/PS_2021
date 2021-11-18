package es.unican.ps.supermercado.negocio.interfaces;

import java.util.Date;
import java.util.Set;

import es.unican.ps.supermercado.negocio.dominio.Articulo;
import es.unican.ps.supermercado.negocio.dominio.LineaPedido;
import es.unican.ps.supermercado.negocio.dominio.Pedido;
import es.unican.ps.supermercado.negocio.dominio.Usuario;

public interface IRealizacionPedidos {

	public Set<Articulo> verArticulos();
	public boolean anhadirAlCarrito(String refArticulo, int numUnidades, Usuario usuario);
	public Set<LineaPedido> verCarrito(Usuario usuario);
	public Pedido confirmarPedido(Date horaDeRecogida, Usuario usuario);
	
}
