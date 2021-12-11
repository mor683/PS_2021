package es.unican.ps.supermercado.dao;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.interfaces.IArticulosDAOLocal;
import es.unican.ps.supermercado.common.interfaces.IArticulosDAORemote;



@Stateless
public class ArticulosDAO implements IArticulosDAOLocal, IArticulosDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;

	@Override
	public Articulo crearArticulo(Articulo a) {
		// Hacemos persistente el articulo
		try {
			em.persist(a);
		} catch (EntityExistsException e) {
			// Ya existe un articulo en la BD con ese nombre
			return null;
		}
		return a;
	}

	@Override
	public Articulo modificarArticulo(Articulo a) {
		// Modificamos el articulo o le creamos si no existe
		try {
			em.merge(a);
		} catch (IllegalArgumentException e) {
			// No existe el articulo
			return null;
		}
		return a;
	}

	@Override
	public Articulo eliminarArticulo(Articulo a) {
		// Eliminamos el articulo
		try {
			em.remove(a);
		} catch (IllegalArgumentException e) {
			// No existe el articulo
			return null;
		}
		return a;
	}

	@Override
	public Articulo articuloPorId(long id) {
		Articulo a;
		// Buscamos el articulo con el nombre indicado
		try {
			a = em.find(Articulo.class, id);
		} catch (IllegalArgumentException e) {
			// No existe el articulo
			return null;
		}
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Articulo> articulos() {
		Query q = em.createQuery("SELECT a from Articulo a");
		return (Set<Articulo>) q.getResultList();
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
