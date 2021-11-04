package es.unican.ps.supermercado.negocio.interfaces;

import es.unican.ps.supermercado.negocio.dominio.Usuario;

public interface IGestionUsuarios {

	public Usuario registrar(Usuario u);
	public boolean login(String dni);
	
}
