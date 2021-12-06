package es.unican.ps.supermercado.common.interfaces;


import java.util.Set;

import es.unican.ps.supermercado.common.dominio.Pedido;

public interface IPedidosDAO {

	public Pedido crearPedido(Pedido p);
	public Pedido modificarPedido(Pedido p);
	public Pedido eliminarPedido(Pedido p);
	public Pedido pedidoPorReferencia(String ref);
	public Set<Pedido> pedidos();
	
}
