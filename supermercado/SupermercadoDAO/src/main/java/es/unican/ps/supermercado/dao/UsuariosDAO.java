package es.unican.ps.supermercado.dao;

import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.negocio.dominio.Usuario;
import es.unican.ps.supermercado.negocio.interfaces.IUsuariosDAOLocal;
import es.unican.ps.supermercado.negocio.interfaces.IUsuariosDAORemote;


@Stateless
public class UsuariosDAO implements IUsuariosDAOLocal, IUsuariosDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;

	public Usuario crearUsuario(Usuario u) {
		// Hacemos persistente el usuario
		try {
			em.persist(u);
		} catch (EntityExistsException e) {
			// Ya existe un usuario en la BD con ese dni
			return null;
		}
		return u;
	}

	public Usuario modificarUsuario(Usuario u) {
		// Modificamos el usuario
		try {
			em.merge(u);
		} catch (EntityExistsException e) {
			// No existe el usuario
			return null;
		}
		return u;
	}

	public Usuario eliminarUsuario(Usuario u) {
		// Eliminamos el usuario
		try {
			em.remove(u);
		} catch (EntityExistsException e) {
			// No existe el usuario
			return null;
		}
		return u;
	}

	public Usuario usuarioPorDni(String dni) {
		Usuario u;
		// Buscamos el usuario con el dni indicado
		try {
			u = em.find(Usuario.class, dni);
		} catch (EntityExistsException e) {
			// No existe el usuario
			return null;
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	public Set<Usuario> usuarios() {
		Query q = em.createQuery("SELECT u from Usuario u");
		return (Set<Usuario>) q.getResultList();
	}

}
