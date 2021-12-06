package es.unican.ps.supermercado.common.interfaces;

import java.util.Set;

import es.unican.ps.supermercado.common.dominio.LineaPedido;

public interface ILineasPedidoDAO {
	
	public LineaPedido crearLineaPedido(LineaPedido l);
	public LineaPedido modificarLineaPedido(LineaPedido l);
	public LineaPedido eliminarLineaPedido(LineaPedido l);
	public LineaPedido lineaPedidoPorId(long id);
	public Set<LineaPedido> lineasPedido();
}
