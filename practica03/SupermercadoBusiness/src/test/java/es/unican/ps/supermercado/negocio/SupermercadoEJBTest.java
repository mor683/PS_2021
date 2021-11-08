package es.unican.ps.supermercado.negocio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.unican.ps.supermercado.negocio.dominio.Articulo;
import es.unican.ps.supermercado.negocio.interfaces.IArticulosDAO;

public class SupermercadoEJBTest {

	// Creacion del SUT
	private static GestionArticulos sut;

	private static IArticulosDAO mockArticulosDAO = mock(IArticulosDAO.class);
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();	

	@BeforeClass
	public static void inicializa() {
		sut = new GestionArticulos(mockArticulosDAO);
	}
	
	// UT.3 - Clase GestionArticulos
	@Test
	public void testActualizarStock() {
		Articulo detergente = new Articulo("Detergente", 30, 12.20);
		Articulo detergenteActualizado = new Articulo("Detergente", 35, 12.20);
		
		// UT.3a
		when(mockArticulosDAO.modificarArticulo(detergente)).thenReturn(detergenteActualizado);
		when(mockArticulosDAO.articuloPorNombre("Detergente")).thenReturn(detergente);
		assertEquals(sut.actualizarStock(detergente, 35).getUnidadesStock(), detergenteActualizado.getUnidadesStock());
		
		// UT.3b
		assertEquals(sut.actualizarStock(detergente, -10), null);
		
		// UT.3c
		Articulo aceite = new Articulo("Aceite", 10, 4.50);
		assertEquals(sut.actualizarStock(aceite, 15), null);
	}

}
