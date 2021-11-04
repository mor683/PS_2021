package es.unican.ps.supermercado.negocio.dominio;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Pedido {
	
	private String referencia;
	private String estado;
	private Date fecha;
	private Date horaRecogida;
	private Usuario usuario;
	private Set<LineaPedido> lineasPedido = new HashSet<LineaPedido>();
	
	// TODO: constructor y metodos
	public Pedido() {}
}
