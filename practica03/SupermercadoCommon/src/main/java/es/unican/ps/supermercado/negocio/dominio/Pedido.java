package es.unican.ps.supermercado.negocio.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Pedidos")
public class Pedido implements Serializable {
	
	@Id
	private String referencia;
	
	private String estado;	// Puede ser Procesado, En Reparto o Entregado.
	
	private Date fecha;
	
	@Column(name="Hora_De_Recogida")
	private Date horaRecogida;
	
	@ManyToOne
	@JoinColumn(name="Usuario_FK")
	private Usuario usuario;
	
	@OneToMany
	@JoinColumn(name="Pedido_FK")
	private Set<LineaPedido> lineasPedido = new HashSet<LineaPedido>();
	
	public Pedido(Usuario usuario, Set<LineaPedido> lineasPedido) {
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
	
	public double getPrecio() {
		double precio = 0;
		for (LineaPedido l : lineasPedido) {
			precio += l.getCantidad() * l.getArticulo().getPrecio();
		}
		return precio;
	}
}
