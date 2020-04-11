package es.ubu.gii.edat.pr03_1920;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.ubu.gii.edat.pr03_1920.HashMapTable;
import es.ubu.gii.edat.pr03_1920.HashMapTable.HashMapCell;
import es.ubu.gii.edat.pr03_1920.Table;

public class TableTests{

	Table <String,Integer,String> tabla;

	@BeforeEach
	public void setUp() throws Exception {
		tabla = new HashMapTable<String,Integer,String>();
	}

	@AfterEach
	public void tearDown() throws Exception {
		tabla.clear();
	}

	@Test
	public void testPut() {

		int k = 0;

		for(int i=0; i<5; i++){
			assertNull(tabla.put(Integer.toString(i), i, "Contenido_"+(k++)));
		}

		assertEquals(5,tabla.size());

		for(int j=1; j<6; j++){
			assertNull(tabla.put(Integer.toString(j), (j-1), "Contenido_"+(k++)));
		}

		assertEquals(10,tabla.size());

		for(int i=0; i<6; i++){
			System.out.println(tabla.row(Integer.toString(i)));
		}

	}

	// TODO Tests sobreescribir elemento cuyas claves ya existen

	@Test
	public void testRemove() {
		testPut();
		
		assertEquals("Contenido_5",tabla.remove("1", 0));
		assertEquals(9,tabla.size());

		assertEquals("Contenido_1",tabla.remove("1", 1));
		assertEquals(8,tabla.size());

	}

	@Test
	public void testGet() {
		testPut();
		assertEquals("Contenido_1",tabla.get("1", 1));
		assertEquals("Contenido_8",tabla.get("4", 3));
		assertEquals("Contenido_6",tabla.get("2", 1));
	}

	// TODO - Accesos en elementos vacíos

	@Test
	public void testContainsKeys() {

		testPut();

		assertTrue(tabla.containsKeys("1", 0));
		assertTrue(tabla.containsKeys("0", 0));
		
		assertTrue(tabla.containsKeys("3", 2));
		assertTrue(tabla.containsKeys("3", 3));

	}
	
	// TODO - Claves que no están insertadas
	// TODO - Claves en orden inverso
	
	@Test
	public void testContainsValue() {
		
		testPut();
		
		assertTrue(tabla.containsValue("Contenido_1"));
		assertTrue(tabla.containsValue("Contenido_3"));
		assertTrue(tabla.containsValue("Contenido_9"));
		
	}

	@Test
	public void testRow() {

		testPut();

		Map<Integer,String> mapa = tabla.row("3");

		assertTrue(mapa.containsKey(2));
		assertTrue(mapa.containsKey(3));

		// Pasarlo a otro test ???????

		assertEquals("Contenido_7",mapa.get(2));
		assertEquals("Contenido_3",mapa.get(3));

		assertFalse(mapa.containsKey(1));
		assertFalse(mapa.containsKey(4));
		assertTrue(mapa.containsValue("Contenido_7"));
		assertTrue(mapa.containsValue("Contenido_3"));


		assertEquals(2,mapa.size());
	}

	@Test
	public void testColumn() {

		testPut();

		Map<String,String> mapa = tabla.column(4);

		assertTrue(mapa.containsKey("4"));
		assertTrue(mapa.containsKey("5"));

		// Pasarlo a otro test ???????

		assertEquals("Contenido_4",mapa.get("4"));
		assertEquals("Contenido_9",mapa.get("5"));

		assertFalse(mapa.containsKey(1));
		assertFalse(mapa.containsKey(4));
		assertTrue(mapa.containsValue("Contenido_4"));
		assertTrue(mapa.containsValue("Contenido_9"));


		assertEquals(2,mapa.size());
	}

	@Test
	public void testSize() {
		
		assertEquals(0,tabla.size());
		testPut();
		assertEquals(10,tabla.size());
		
	}

	@Test
	public void testIsEmpty() {
		assertTrue(tabla.isEmpty());
		testPut();
		assertFalse(tabla.isEmpty());
	}

	@Test
	public void testCellSet() {
		testPut();

		Collection<Table.Cell<String, Integer, String>> cellSet = tabla.cellSet();

		assertEquals(cellSet.size(), tabla.size());
		//Para todos los elementos en la colección
		boolean estanTodos = true;
		for (Table.Cell <String, Integer, String> celda : cellSet) {
			if (!tabla.containsValue(celda.getValue()))
				estanTodos = false;
		}

		assertTrue(estanTodos);
		
		for (Table.Cell <String, Integer, String> celda : cellSet) {
			celda.setValue("Cambiado");
		}
		
	
		assertTrue(tabla.get(1, 1) == "Cambiado" );
	}

}
