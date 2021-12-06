package es.unican.ps.supermercado.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import es.unican.ps.supermercado.negocio.dominio.Articulo;


public class ArticulosDAOTest {

	// Creacion del SUT
	private ArticulosDAO sut;
	
	@PersistenceContext(unitName="supermercadoPU")
	private EntityManager em;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();	

	private Articulo detergente;
	private Articulo aceite;

	@Before
	public void setUp() {
		sut = new ArticulosDAO();

		detergente = new Articulo("Detergente", 30, 12.20);
		aceite = new Articulo("Aceite", 15, 4.50);
		
		// programacion comun de mocks
	}

	// UT.4 - Clase ArticulosDAO
	@Test
	public void testCrearArticulo() {

		// UT.4a
		assertEquals(sut.crearArticulo(aceite), aceite);

		// UT.4b
		assertEquals(sut.crearArticulo(detergente), null);
	}

}
