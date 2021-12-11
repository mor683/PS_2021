package es.unican.ps.supermercado.common.interfaces;


import java.util.Set;

import es.unican.ps.supermercado.common.dominio.Articulo;

public interface IArticulosDAO {
	
	public Articulo crearArticulo(Articulo a);
	public Articulo modificarArticulo(Articulo a);
	public Articulo eliminarArticulo(Articulo a);
	public Articulo articuloPorId(long id);
	public Set<Articulo> articulos();

}
