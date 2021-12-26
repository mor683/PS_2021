package es.unican.ps.supermercado.common.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import es.unican.ps.supermercado.common.dominio.*;

public interface IRealizacionPedidos {

	public List<Articulo> verArticulos();
	public boolean anhadirAlCarrito(long id, int numUnidades, Usuario usuario);
	public List<LineaPedido> verCarrito(Usuario usuario);
	public Pedido confirmarPedido(Date horaDeRecogida, Usuario usuario);
	
}
