package es.unican.ps.supermercado.negocio.dominio;


public class LineaPedido {

	private double cantidad;
	private Articulo articulo;
	
	public LineaPedido(double cantidad, Articulo articulo) {
		this.cantidad = cantidad;
		this.setArticulo(articulo);
	}
	
	public double getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
}
