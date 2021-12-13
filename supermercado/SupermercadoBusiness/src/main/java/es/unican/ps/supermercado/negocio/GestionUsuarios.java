package es.unican.ps.supermercado.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.unican.ps.supermercado.common.dominio.Usuario;
import es.unican.ps.supermercado.common.interfaces.IGestionUsuariosLocal;
import es.unican.ps.supermercado.common.interfaces.IGestionUsuariosRemote;
import es.unican.ps.supermercado.common.interfaces.IUsuariosDAORemote;



@Stateless
public class GestionUsuarios implements IGestionUsuariosLocal, IGestionUsuariosRemote {

	@EJB
    private IUsuariosDAORemote usuariosDAO;
	
	public GestionUsuarios() {}
	
	public Usuario registrar(Usuario u) {
		Usuario usuario = usuariosDAO.usuarioPorDni(u.getDni());
		if (usuario != null) {
			usuario = usuariosDAO.crearUsuario(u);
		}
		return usuario;
	}

	public Usuario login(String dni) {
		Usuario u = usuariosDAO.usuarioPorDni(dni);
		return u;
	}

}
