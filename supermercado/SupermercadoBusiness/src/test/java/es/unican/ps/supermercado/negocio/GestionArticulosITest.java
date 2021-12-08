package es.unican.ps.supermercado.negocio;

import static org.junit.Assert.assertEquals;

import javax.ejb.embeddable.EJBContainer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.interfaces.IArticulosDAO;
import es.unican.ps.supermercado.dao.ArticulosDAO;

public class GestionArticulosITest {

	// Creacion del SUT
	private static GestionArticulos sut;

	private IArticulosDAO articulosDAO;

	// Creacion contenedor embebido
	private static EJBContainer ec;

	private Articulo detergente;
	private Articulo detergenteActualizado;
	private Articulo aceite;


	@BeforeClass
	public static void initContainer() throws Exception {
		// Creamos el contenedor embebido
		ec = EJBContainer.createEJBContainer();
		// Buscamos el EJB
		sut= (GestionArticulos) ec.getContext().lookup("java:global/SupermercadoBusiness/GestionArticulos"
				+ "!es.unican.ps.supermercado.common.interfaces.IGestionArticulosRemote");
	}
	
	@AfterClass
	public static void closeContainer() throws Exception {
		//Cerramos el contenedor
		if(ec!= null) {
			ec.close();
		}
	}

	@Before
	public void setUp() {
		articulosDAO = new ArticulosDAO();
		sut = new GestionArticulos(articulosDAO);

		detergente = new Articulo("Detergente", 30, 12.20);
		detergenteActualizado = new Articulo("Detergente", 35, 12.20);
		aceite = new Articulo("Aceite", 10, 4.50);
	}

	// IT.3 - Clase GestionArticulos
	@Test
	public void testActualizarStock() {

		// IT.3a
		assertEquals(sut.actualizarStock(detergente, 35).getUnidadesStock(), detergenteActualizado.getUnidadesStock());

		// IT.3b
		assertEquals(sut.actualizarStock(detergente, -10), null);

		// IT.3c
		assertEquals(sut.actualizarStock(aceite, 15), null);
	}

}
