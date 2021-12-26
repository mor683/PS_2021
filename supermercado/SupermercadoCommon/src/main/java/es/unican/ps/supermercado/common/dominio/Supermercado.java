package es.unican.ps.supermercado.common.dominio;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Supermercado {

	private Date horaApertura;
	private Date horaCierre;
	private List<Usuario> usuarios = new LinkedList<Usuario>();
	private List<Articulo> articulos = new LinkedList<Articulo>();
	
	public Supermercado(Date horaApertura, Date horaCierre) {
		this.ListHoraApertura(horaApertura);
		this.ListHoraCierre(horaCierre);
	}

	public Date getHoraApertura() {
		return horaApertura;
	}

	public void ListHoraApertura(Date horaApertura) {
		this.horaApertura = horaApertura;
	}

	public Date getHoraCierre() {
		return horaCierre;
	}

	public void ListHoraCierre(Date horaCierre) {
		this.horaCierre = horaCierre;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void ListUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void ListArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

}
