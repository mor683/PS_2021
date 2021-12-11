package es.unican.ps.supermercado.common.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
}
