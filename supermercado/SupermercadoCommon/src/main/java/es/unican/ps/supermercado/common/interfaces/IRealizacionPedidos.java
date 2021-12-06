package es.unican.ps.supermercado.common.interfaces;

import java.util.Date;
import java.util.Set;

import es.unican.ps.supermercado.common.dominio.*;

public interface IRealizacionPedidos {

	public Set<Articulo> verArticulos();
	public boolean anhadirAlCarrito(String refArticulo, int numUnidades, Usuario usuario);
	public Set<LineaPedido> verCarrito(Usuario usuario);
	public Pedido confirmarPedido(Date horaDeRecogida, Usuario usuario);
	
}
