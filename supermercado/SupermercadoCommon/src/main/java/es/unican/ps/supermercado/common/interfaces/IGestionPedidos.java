package es.unican.ps.supermercado.common.interfaces;

import es.unican.ps.supermercado.common.dominio.Pedido;

public interface IGestionPedidos {

	public double entregarPedido(String ref, String dni);
	public Pedido procesarPedido();
	
}
