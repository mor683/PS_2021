package es.unican.ps.supermercado.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.unican.ps.supermercado.common.dominio.Usuario;
import es.unican.ps.supermercado.common.interfaces.IUsuariosDAOLocal;
import es.unican.ps.supermercado.common.interfaces.IUsuariosDAORemote;


@Stateless
public class UsuariosDAO implements IUsuariosDAOLocal, IUsuariosDAORemote {

	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;
	
	@Resource
	TimerService timerService;


	public Usuario crearUsuario(Usuario u) {
		// Hacemos persistente el usuario
		try {
			em.persist(u);
			
			// Programación de un evento para resetear las compras mensuales del cliente
			ScheduleExpression initialDay = new ScheduleExpression().dayOfMonth(1);
			timerService.createCalendarTimer(initialDay, new TimerConfig(u, true));
		} catch (EntityExistsException e) {
			// Ya existe un usuario en la BD con ese dni
			return null;
		}
		return u;
	}

	public Usuario modificarUsuario(Usuario u) {
		// Modificamos el usuario o le creamos si no existe
		try {
			em.merge(u);
		} catch (IllegalArgumentException e) {
			// No existe el usuario
			return null;
		}
		return u;
	}

	public Usuario eliminarUsuario(Usuario u) {
		// Eliminamos el usuario
		try {
			em.remove(u);
		} catch (IllegalArgumentException e) {
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
		} catch (IllegalArgumentException e) {
			// No existe el usuario
			return null;
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> usuarios() {
		Query q = em.createQuery("SELECT u from Usuario u");
		return q.getResultList();
	}
	
	@Timeout
	public void resetearComprasMensuales(Timer timer) {
		Usuario u = (Usuario) timer.getInfo();
		u.setComprasMensuales(0);
		this.modificarUsuario(u);
		return;
	}

}
