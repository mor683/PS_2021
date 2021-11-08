package es.unican.ps.supermercado.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.unican.ps.supermercado.negocio.dominio.Articulo;
import es.unican.ps.supermercado.negocio.interfaces.IArticulosDAO;
import es.unican.ps.supermercado.negocio.interfaces.IGestionArticulosLocal;
import es.unican.ps.supermercado.negocio.interfaces.IGestionArticulosRemote;

@Stateless
public class GestionArticulos implements IGestionArticulosLocal, IGestionArticulosRemote {

	@EJB
	private IArticulosDAO articulosDAO;
	
	public GestionArticulos(IArticulosDAO articulosDAO) {
		this.articulosDAO = articulosDAO;
	}

	public Articulo anhadirArticulo(Articulo a) {
		Articulo articulo = null;

		// Si el articulo no existe, se añade a la BD
		if (articulosDAO.articuloPorNombre(a.getNombre()) == null) {
			articulo = articulosDAO.crearArticulo(a);
		}

		return articulo;
	}

	public Articulo eliminarArticulo(Articulo a) {
		Articulo articulo = null;

		// Si el articulo no existe, no se elimina de la BD
		if (articulosDAO.articuloPorNombre(a.getNombre()) != null) {
			articulo = articulosDAO.eliminarArticulo(a);
		}

		return articulo;
	}

	public Articulo actualizarStock(Articulo a, int unidades) {
		Articulo articulo = null;
		
		// Si el numero de unidades es mayor que 0 se actualiza
		if(unidades >= 0) {
			// Si el articulo existe, se modifica
			if (articulosDAO.articuloPorNombre(a.getNombre()) != null) {
				a.setUnidadesStock(unidades);
				articulo = articulosDAO.modificarArticulo(a);
			}
		}
		
		return articulo;
	}

}