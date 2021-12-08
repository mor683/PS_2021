package es.unican.ps.supermercado.negocio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.interfaces.IArticulosDAO;


public class GestionArticulosTest {

	// Creacion del SUT
	private GestionArticulos sut;

	@Mock
	private IArticulosDAO mockArticulosDAO;
	
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
	
	// UT.3 - Clase GestionArticulos
	@Test
	public void testActualizarStock() {
		
		// UT.3a
		assertEquals(sut.actualizarStock(detergente, 35).getUnidadesStock(), detergenteActualizado.getUnidadesStock());
		
		// UT.3b
		assertEquals(sut.actualizarStock(detergente, -10), null);
		
		// UT.3c
		assertEquals(sut.actualizarStock(aceite, 15), null);
	}

}
