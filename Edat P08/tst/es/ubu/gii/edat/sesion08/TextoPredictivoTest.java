package es.ubu.gii.edat.sesion08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TextoPredictivoTest {

	TextoPredictivo predictivo;
	
	String [] diccionario = {"a","ante","bajo","cabe","con","contra","según","sin","sobre","tras"};
	
	@Before
	public void setUp() throws Exception {
		predictivo = new TextoPredictivo(diccionario);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTextoPredictivo() {
		assertEquals(10,predictivo.tamañoDiccionario());
	}

	@Test
	public void testSugiere() {
		
		String [] sugerencias;
		
		sugerencias = predictivo.sugiere("ba", 3);

		assertEquals("ante",sugerencias[0]);
		assertEquals("bajo",sugerencias[1]);
		assertEquals("cabe",sugerencias[2]);
		
		sugerencias = predictivo.sugiere("sob", 3);
		assertEquals("sin",sugerencias[0]);
		assertEquals("sobre",sugerencias[1]);
		assertEquals("tras",sugerencias[2]);

		/* Tests propios
		 * Para verificar el comportamiento.
		 */

		sugerencias = predictivo.sugiere("sob", 1);
		assertEquals("sobre",sugerencias[0]);

		sugerencias = predictivo.sugiere("sobre", 3);
		assertEquals(sugerencias.length, 1);
		assertEquals("sobre",sugerencias[0]);

		sugerencias = predictivo.sugiere("si", 1);
		assertEquals("sin",sugerencias[0]);


		//está en el medio
		sugerencias = predictivo.sugiere("si", 7);
		assertEquals("sin",sugerencias[3]);

		sugerencias = predictivo.sugiere("si", 30);
		assertEquals("sin",sugerencias[15]);

	}
}
