package es.unican.ps.supermercado.common.interfaces;

import es.unican.ps.supermercado.common.dominio.Usuario;

public interface IGestionUsuarios {

	public Usuario registrar(Usuario u);
	public Usuario login(String dni);
	
}
