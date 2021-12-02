import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.unican.ps.supermercado.dao.ArticulosDAO;
import es.unican.ps.supermercado.negocio.GestionArticulos;
import es.unican.ps.supermercado.negocio.dominio.Articulo;
import es.unican.ps.supermercado.negocio.interfaces.IArticulosDAO;

public class ArticulosDAOTest {

	// Creacion del SUT
	private ArticulosDAO sut;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();	

	private Articulo detergente;
	private Articulo detergenteActualizado;
	private Articulo aceite;

	@Before
	public void setUp() {
		sut = new GestionArticulos(mockArticulosDAO);

		// programacion comun de mocks
		detergente = new Articulo("Detergente", 30, 12.20);
		detergenteActualizado = new Articulo("Detergente", 35, 12.20);
		aceite = new Articulo("Aceite", 10, 4.50);

		when(mockArticulosDAO.modificarArticulo(detergente)).thenReturn(detergenteActualizado);
		when(mockArticulosDAO.articuloPorNombre("Detergente")).thenReturn(detergente);
	}

	// UT.4 - Clase ArticulosDAO
	@Test
	public void testCrearArticulo() {

		// UT.4a
		assertEquals(sut.crearArticulo(), detergenteActualizado.getUnidadesStock());

		// UT.4b
		assertEquals(sut.actualizarStock(detergente, -10), null);
	}

}
