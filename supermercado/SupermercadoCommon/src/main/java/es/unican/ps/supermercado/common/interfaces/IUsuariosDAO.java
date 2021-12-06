package es.unican.ps.supermercado.common.interfaces;


import java.util.Set;

import es.unican.ps.supermercado.common.dominio.Usuario;

public interface IUsuariosDAO {

	public Usuario crearUsuario(Usuario u);
	public Usuario modificarUsuario(Usuario u);
	public Usuario eliminarUsuario(Usuario u);
	public Usuario usuarioPorDni(String dni);
	public Set<Usuario> usuarios();
	
}
