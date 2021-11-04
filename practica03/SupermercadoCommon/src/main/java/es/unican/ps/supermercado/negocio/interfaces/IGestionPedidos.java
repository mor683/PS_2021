package es.unican.ps.supermercado.negocio.interfaces;

import es.unican.ps.supermercado.negocio.dominio.Pedido;

public interface IGestionPedidos {

	public double entregarPedido(String ref, String dni);
	public Pedido procesarPedido();
	
}
