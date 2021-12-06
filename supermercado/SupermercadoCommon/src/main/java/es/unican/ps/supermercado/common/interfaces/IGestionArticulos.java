package es.unican.ps.supermercado.common.interfaces;

import es.unican.ps.supermercado.common.dominio.Articulo;

public interface IGestionArticulos {

	public Articulo anhadirArticulo(Articulo a);
	public Articulo eliminarArticulo(Articulo a);
	public Articulo actualizarStock(Articulo a, int unidades);
	
}
