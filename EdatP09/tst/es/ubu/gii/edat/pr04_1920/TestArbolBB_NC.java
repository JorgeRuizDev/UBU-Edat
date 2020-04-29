package es.ubu.gii.edat.pr04_1920;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.ubu.gii.edat.pr04_1920.utils.*;

public class TestArbolBB_NC {

	ArbolBB<Coche> arbol_NoComp;
	List<Integer> datos;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		arbol_NoComp.clear();
	}

	@Test
	public void testAdd_NoComparables_NoRepetidos() {
		
		List<Coche> datosNC = this.generaNoComparables();

		arbol_NoComp = new ArbolBB<Coche>( datosNC, new ComparaCoches<>() );
		
		assertEquals(datosNC.size(), arbol_NoComp.size());
		assertTrue(datosNC.containsAll(arbol_NoComp));
		
	}
	
	@Test
	public void testAdd_NoComparables_Repetidos() {

		List<Coche> datosNC = this.generaNoComparables();
		List<Coche> repetidos = new ArrayList<Coche>(datosNC.size()*2);
		
		repetidos.addAll(datosNC);
		repetidos.addAll(datosNC);
		
		arbol_NoComp = new ArbolBB<Coche>( repetidos, new ComparaCoches<>() );
		
		assertEquals(datosNC.size(), arbol_NoComp.size());
		assertTrue(datosNC.containsAll(arbol_NoComp));
		
	}
	
	protected List<Coche> generaNoComparables(){
		
		List<Coche> datos = GeneradorCoches.listaSecuencial(9);
	
		Collections.swap(datos, 0, 4);
		Collections.swap(datos, 1, 7);
		Collections.swap(datos, 5, 8);

		return datos;
		
	}
	
}
