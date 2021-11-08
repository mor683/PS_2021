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

	public Articulo anhadirArticulo(Articulo a) {
		return articulosDAO.crearArticulo(a);
	}

	public Articulo eliminarArticulo(Articulo a) {
		return articulosDAO.eliminarArticulo(a);
	}

	public Articulo actualizarStock(Articulo a, int unidades) {
		if(unidades >= 0) {
			a.setUnidadesStock(unidades);
		}
		return articulosDAO.modificarArticulo(a);
	}

}