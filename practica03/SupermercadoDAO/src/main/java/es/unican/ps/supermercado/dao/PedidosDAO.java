package es.unican.ps.supermercado.dao;

import java.util.Set;

import es.unican.ps.supermercado.negocio.dominio.Articulo;
import es.unican.ps.supermercado.negocio.dominio.Pedido;

@Stateless
public class PedidosDAO implements IPedidosDAOLocal, IPedidosDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;

	@Override
	public Pedido crearPedido(Pedido p) {
		// Hacemos persistente el pedido
		try {
			em.persist(p);
		} catch (EntityExistsException e) {
			// Ya existe un pedido en la BD con esa referencia
			return null;
		}
		return p;
	}

	@Override
	public Pedido modificarPedido(Pedido p) {
		// Modificamos el pedido
		try {
			em.merge(p);
		} catch (EntityExistsException e) {
			// No existe el pedido
			return null;
		}
		return p;
	}

	@Override
	public Pedido eliminarPedido(Pedido p) {
		// Eliminamos el pedido
		try {
			em.remove(p);
		} catch (EntityExistsException e) {
			// No existe el pedido
			return null;
		}
		return p;
	}

	@Override
	public Pedido pedidoPorReferencia(String ref) {
		Pedido p;
		// Buscamos el pedido con la referencia indicada
		try {
			p = em.find(Pedido.class, ref);
		} catch (EntityExistsException e) {
			// No existe el pedido
			return null;
		}
		return p;
	}

	@Override
	public Set<Pedido> pedidos() {
		Query q = em.createQuery("SELECT p from Pedido p");
		return (Set<Pedido>) q.getResultList();
	}
}
