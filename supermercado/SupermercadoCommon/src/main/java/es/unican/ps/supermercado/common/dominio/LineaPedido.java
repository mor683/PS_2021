package es.unican.ps.supermercado.common.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Lineas_Pedido")
public class LineaPedido implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name="Usuario_FK")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="Pedido_FK")
	private Pedido pedido;
	
	@OneToOne
	@JoinColumn(name="Articulo_FK")
	private Articulo articulo;
	
	public LineaPedido() {
		
	}
	
	public LineaPedido(int cantidad, Articulo articulo) {
		this.cantidad = cantidad;
		this.setArticulo(articulo);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
}
