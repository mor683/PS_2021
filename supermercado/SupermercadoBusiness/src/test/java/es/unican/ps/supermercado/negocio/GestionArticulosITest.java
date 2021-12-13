package es.unican.ps.supermercado.negocio;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.interfaces.IGestionArticulosRemote;

public class GestionArticulosITest {

	// Creacion del SUT
	private static IGestionArticulosRemote sut;

	// Creacion contenedor embebido
	private static EJBContainer ec;

	private static Articulo detergente;
	private Articulo aceite;


	@BeforeClass
	public static void initContainer() throws Exception {
		Map properties = new HashMap();
		properties.put(EJBContainer.MODULES, new File[]{new File("classes")});
		// "C:/Escritorio/4_CURSO/PROCESOS_SOFTWARE/Recursos/glassfish-5.0.1/glassfish5/glassfish"
		// C:\\Escritorio\\4_CURSO\\PROCESOS_SOFTWARE\\Recursos\\glassfish-5.0.1\\glassfish5\\glassfish
		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root",
				"C:/Escritorio/4_CURSO/PROCESOS_SOFTWARE/Recursos/glassfish-5.0.1/glassfish5/glassfish");    // TODO: cambiar la direccion de glassfish
		// Creación del EJBContainer con propiedades
		ec = EJBContainer.createEJBContainer(properties);

		// Buscamos el EJB
		sut= (IGestionArticulosRemote) ec.getContext().lookup("java:global/ejb-app/classes/GestionArticulos!es.unican.ps.supermercado.common.interfaces.IGestionArticulosRemote");
	}
	// java:global/ejb-app/classes/GestionArticulos!es.unican.ps.supermercado.common.interfaces.IGestionArticulosLocal
	@AfterClass
	public static void closeContainer() throws Exception {
		// Reestablecer la base de datos
		sut.actualizarStock(detergente, 30);
		// Cerramos el contenedor
		if(ec!= null) {
			ec.close();
		}
	}

	@Before
	public void setUp() {
		detergente = new Articulo("Detergente", 30, 12.20);
		detergente.setId(1);
		aceite = new Articulo("Aceite", 10, 4.50);
		aceite.setId(2);
	}

	// IT.3 - Clase GestionArticulos
	@Test
	public void testActualizarStock() {

		// IT.3a
		assertEquals(sut.actualizarStock(detergente, 35).getUnidadesStock(), 35);

		// IT.3b
		assertEquals(sut.actualizarStock(detergente, -10), null);

		// IT.3c
		assertEquals(sut.actualizarStock(aceite, 15), null);
	}

}
