package es.unican.ps.supermercado.negocio.interfaces;


import java.util.Set;

import es.unican.ps.supermercado.negocio.dominio.Articulo;

public interface IArticulosDAO {
	
	public Articulo crearArticulo(Articulo a);
	public Articulo modificarArticulo(Articulo a);
	public Articulo eliminarArticulo(Articulo a);
	public Articulo articuloPorNombre(String nombre);
	public Set<Articulo> articulos();

}
