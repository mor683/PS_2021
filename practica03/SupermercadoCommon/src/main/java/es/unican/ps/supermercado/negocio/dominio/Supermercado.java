package es.unican.ps.supermercado.negocio.dominio;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Supermercado {

	private Date horaApertura;
	private Date horaCierre;
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	private Set<Articulo> articulos = new HashSet<Articulo>();
	
	public Supermercado(Date horaApertura, Date horaCierre) {
		this.setHoraApertura(horaApertura);
		this.setHoraCierre(horaCierre);
	}

	public Date getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Date getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(Set<Articulo> articulos) {
		this.articulos = articulos;
	}

}
