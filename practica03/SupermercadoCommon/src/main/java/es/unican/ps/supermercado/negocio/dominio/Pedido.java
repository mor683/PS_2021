package es.unican.ps.supermercado.negocio.dominio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Pedido {
	
	private String referencia;
	private String estado;	// Puede ser Admitido, En Reparto o Entregado.
	private Date fecha;
	private Date horaRecogida;
	private Usuario usuario;
	private Set<LineaPedido> lineasPedido = new HashSet<LineaPedido>();
	
	public Pedido(String referencia, Usuario usuario, Set<LineaPedido> lineasPedido) {
		this.setReferencia(referencia);
		this.setEstado("Admitido");
		this.setFecha(new Date());
		this.setHoraRecogida(null);
		this.setUsuario(usuario);
		this.lineasPedido = lineasPedido;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHoraRecogida() {
		return horaRecogida;
	}

	public void setHoraRecogida(Date horaRecogida) {
		this.horaRecogida = horaRecogida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}

	public void setLineasPedido(Set<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}
}
