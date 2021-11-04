package es.unican.ps.supermercado.negocio.dominio;


import java.util.Date;

public class Supermercado {

	private Date horaApertura;
	private Date horaCierre;
	
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

}
