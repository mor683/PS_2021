package es.unican.ps.supermercado.negocio.dominio;


public class LineaPedido {

	private int cantidad;
	private Articulo articulo;
	
	public LineaPedido(int cantidad, Articulo articulo) {
		this.cantidad = cantidad;
		this.setArticulo(articulo);
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
