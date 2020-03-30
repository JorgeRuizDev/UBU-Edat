package es.ubu.gii.edat.sesion06;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.gii.edat.sesion06.TraductorAvanzado;

public class TraductorAvanzadoTest {

	TraductorAvanzado traduccionAvanzada;
	
	@Before
	public void setUp() throws Exception {
		traduccionAvanzada = new TraductorAvanzado();
	}

	@After
	public void tearDown() throws Exception {
		traduccionAvanzada.clear();
	}

	@Test
	public void testCargaIdiomas() {

		// Idioma de consulta
		String[] idiomaConsulta = {"free", "free", 
				"dog", "cat", "keyboard", 
				"cool", "cool" , "cool", 
				"available", "available"};
		
		// Idioma de respuesta
		String[] idiomaRespuesta = {"libre", "gratis", 
				"perro", "gato", "teclado", 
				"fresco", "indiferente", "chévere", 
				"libre", "disponible" };
		
		assertEquals(6, traduccionAvanzada.cargaDiccionario(idiomaConsulta, idiomaRespuesta));
		
	}
	
	@Test
	public void testCargaAdicionales() {

		testCargaIdiomas();
		
		String[] idioma1 = {"free", "puerta" };
		String[] idioma2 = {"pasa y pidete algo", "door"};
		System.out.println("");
		assertEquals(7, traduccionAvanzada.cargaDiccionario(idioma1, idioma2));
		
	}
	
	@Test
	public void testBuscaTraduccionSimple() {
		
		testCargaIdiomas();

		assertEquals(Arrays.asList("perro"),traduccionAvanzada.buscaTraduccion("dog"));
		assertEquals(Arrays.asList("gato"),traduccionAvanzada.buscaTraduccion("cat"));
		
	}

	@Test
	public void testBuscaAmbiguas() {
		
		testCargaIdiomas();
		
		List<String> esperadas = Arrays.asList("libre","gratis");
		List<String> recuperadas = traduccionAvanzada.buscaTraduccion("free");
		
		assertEquals(esperadas.size(),recuperadas.size());
		assertTrue(esperadas.containsAll(recuperadas));
		
		esperadas = Arrays.asList("fresco", "indiferente", "chévere");
		recuperadas = traduccionAvanzada.buscaTraduccion("cool");
		
		assertEquals(esperadas.size(),recuperadas.size());
		assertTrue(esperadas.containsAll(recuperadas));
		
		recuperadas = traduccionAvanzada.buscaTraduccion("no hay !!!");
		assertEquals(0,recuperadas.size());
		
	}
	
	@Test
	public void testBuscaSinonimos() {

		testCargaIdiomas();

		List<String> sinonims = traduccionAvanzada.buscaSinonimos("libre");
		List<String> expected = Arrays.asList("gratis", "disponible");
		
		assertTrue( sinonims.containsAll(expected)  );
		assertEquals( expected.size(), sinonims.size() );
		
	}

	
}
