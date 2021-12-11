package es.unican.ps.supermercado.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.interfaces.IArticulosDAORemote;
import es.unican.ps.supermercado.common.interfaces.IGestionArticulosLocal;
import es.unican.ps.supermercado.common.interfaces.IGestionArticulosRemote;


@Stateless
public class GestionArticulos implements IGestionArticulosLocal, IGestionArticulosRemote {

	@EJB
	private IArticulosDAORemote articulosDAO;
	
	public GestionArticulos() {}
	
	public GestionArticulos(IArticulosDAORemote articulosDAO) {
		this.articulosDAO = articulosDAO;
	}

	public Articulo anhadirArticulo(Articulo a) {
		Articulo articulo = null;

		// Si el articulo no existe, se añade a la BD
		if (articulosDAO.articuloPorId(a.getId()) == null) {
			articulo = articulosDAO.crearArticulo(a);
		}

		return articulo;
	}

	public Articulo eliminarArticulo(Articulo a) {
		Articulo articulo = null;

		// Si el articulo no existe, no se elimina de la BD
		if (articulosDAO.articuloPorId(a.getId()) != null) {
			articulo = articulosDAO.eliminarArticulo(a);
		}

		return articulo;
	}

	public Articulo actualizarStock(Articulo a, int unidades) {
		Articulo articulo = null;
		
		// Si el numero de unidades es mayor que 0 se actualiza
		if(unidades >= 0) {
			// Si el articulo existe, se modifica
			if (articulosDAO.articuloPorId(a.getId()) != null) {
				a.setUnidadesStock(unidades);
				articulo = articulosDAO.modificarArticulo(a);
			}
		}
		
		return articulo;
	}

}