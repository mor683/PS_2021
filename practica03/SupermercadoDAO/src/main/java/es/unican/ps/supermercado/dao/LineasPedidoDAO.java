package es.unican.ps.supermercado.dao;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.negocio.dominio.LineaPedido;
import es.unican.ps.supermercado.negocio.interfaces.ILineasPedidoDAOLocal;
import es.unican.ps.supermercado.negocio.interfaces.ILineasPedidoDAORemote;

@Stateless
public class LineasPedidoDAO implements ILineasPedidoDAOLocal, ILineasPedidoDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;

	@Override
	public LineaPedido crearLineaPedido(LineaPedido l) {
		// Hacemos persistente la LineaPedido
		try {
			em.persist(l);
		} catch (EntityExistsException e) {
			// Ya existe una LineaPedido en la BD con ese id
			return null;
		}
		return l;
	}

	@Override
	public LineaPedido modificarLineaPedido(LineaPedido l) {
		// Modificamos la LineaPedido
		try {
			em.merge(l);
		} catch (EntityExistsException e) {
			// No existe la LineaPedido
			return null;
		}
		return l;
	}

	@Override
	public LineaPedido eliminarLineaPedido(LineaPedido l) {
		// Eliminamos la LineaPedido
		try {
			em.remove(l);
		} catch (EntityExistsException e) {
			// No existe la LineaPedido
			return null;
		}
		return l;
	}

	@Override
	public LineaPedido lineaPedidoPorId(long id) {
		LineaPedido l;
		// Buscamos la LineaPedido con el ID indicado
		try {
			l = em.find(LineaPedido.class, id);
		} catch (EntityExistsException e) {
			// No existe la LineaPedido
			return null;
		}
		return l;
	}

	@Override
	public Set<LineaPedido> lineasPedido() {
		Query q = em.createQuery("SELECT l from Lineas_Pedido l");
		return (Set<LineaPedido>) q.getResultList();
	}
	
}
