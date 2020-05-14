package es.ubu.gii.edat.pr04_1920;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestArbolAVL {

	ArbolAVL<Integer> arbol;
	List<Integer> datos;
	
	@Before
	public void setUp() throws Exception {
		arbol = new ArbolAVL<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		arbol.clear();
	}

	@Test
	public void testAlturaPersonal(){

		datos = Arrays.asList(20,10,40,8,4);
		arbol.addAll(datos);
		assertEquals(0,arbol.altura(4));
		assertEquals(2,arbol.altura(20));
	}
	@Test
	public void testRotacionRaizPersonal(){
		datos = Arrays.asList(1,2,3);
		arbol.addAll(datos);
		assertEquals(0,arbol.altura(1));
		assertEquals(1,arbol.altura(2));
	}
	@Test
	public void testRemovePersonal(){

		datos = Arrays.asList(20,10,40,8,4);
		arbol.addAll(datos);

		assertEquals(0, arbol.altura(10));
		assertEquals(0, arbol.altura(4));
		assertEquals(0, arbol.altura(40));
		assertEquals(1, arbol.altura(8));
		assertEquals(2, arbol.altura(20));

		arbol.remove(8);

		//Cuando se hace remove de un nodo con dos hijos, se mueve uno de sus
		//dos hijos de forma aleatoria, 10 y 4 son hijos de 8, por lo que uno de ellos
		//se quedará abrajo, y otro ocupará la posición de 8

		int elementoPos8;
		if (arbol.altura(10) == 0) {
			System.out.println("4 ocupa la posición de 8");
			elementoPos8=4;
			assertEquals(0, arbol.altura(10));
			assertEquals(1, arbol.altura(4));
			assertEquals(0, arbol.altura(40));
			assertEquals(2, arbol.altura(20));
		}
		else{
			System.out.println("10 ocupa la posición de 8");
			elementoPos8=10;
			assertEquals(1, arbol.altura(10));
			assertEquals(0, arbol.altura(4));
			assertEquals(0, arbol.altura(40));
			assertEquals(2, arbol.altura(20));
		}
		assertEquals(arbol.size() , datos.size() - 1 );

		arbol.remove(40);

		if (elementoPos8 == 4) {
			//assertEquals(1, arbol.altura(10));
			//assertEquals(0, arbol.altura(4));
			assertEquals(0, arbol.altura(20));
		}
		else {
			assertEquals(1, arbol.altura(10));
			assertEquals(0, arbol.altura(4));
			assertEquals(0, arbol.altura(20));
		}

		//añadir Iterador por aquí abajo para comprobar recorrido
	}


	@Test
	public void rotacionDerecha(){
		
		datos = Arrays.asList(20,10,40,8);
		arbol.addAll(datos);

		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(10));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(8));
		assertEquals(4, arbol.size());

		arbol.add(5); // Se fuerza el reequilibrado
		
		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(8));
		assertEquals(0,arbol.altura(10));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(5));
		assertEquals(5, arbol.size());
		
	}

	
	@Test
	public void rotacionDerechaIzquierda(){
		
		datos = Arrays.asList(8,20,5,40);
		arbol.addAll(datos);
	
		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(20));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(40));
		assertEquals(4, arbol.size());
	
		arbol.add(30); // Se fuerza el reequilibrado
		
		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(30));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(20));
		assertEquals(5, arbol.size());
		
	}

	@Test
	public void rotacionIzquierda(){
		
		datos = Arrays.asList(8,20,5,40);
		arbol.addAll(datos);

		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(20));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(40));
		assertEquals(4, arbol.size());

		arbol.add(45); // Se fuerza el reequilibrado
		
		assertEquals(2,arbol.altura(8));
		assertEquals(1,arbol.altura(40));
		assertEquals(0,arbol.altura(45));
		assertEquals(0,arbol.altura(5));
		assertEquals(0,arbol.altura(20));
		assertEquals(5, arbol.size());
		
	}

	
	@Test
	public void rotacionIzquierdaDerecha (){
		
		datos = Arrays.asList(20,10,40,8);
		arbol.addAll(datos);
		
		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(10));
		assertEquals(0,arbol.altura(40));
		assertEquals(0,arbol.altura(8));
		assertEquals(4, arbol.size());
		
		arbol.add(9); // Se fuerza el reequilibrado
		System.out.println(arbol.raiz);
		assertEquals(2,arbol.altura(20));
		assertEquals(1,arbol.altura(9));
		assertEquals(0,arbol.altura(10));
		assertEquals(0,arbol.altura(8));
		assertEquals(0,arbol.altura(40));
		assertEquals(5, arbol.size());
		
	}

	@Test
	public void testProfundidad() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		assertEquals(0, arbol.profundidad(8));
		assertEquals(1, arbol.profundidad(5));
		assertEquals(1, arbol.profundidad(30));
		assertEquals(2, arbol.profundidad(20));
		assertEquals(2, arbol.profundidad(40));
		
	}

	@Test
	public void testInOrden() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		Collections.sort(datos);
		List<Integer> inO = arbol.inOrden();
		
		Iterator<Integer> dIt = datos.iterator();
		Iterator<Integer> inIt = inO.iterator();
		
		while(dIt.hasNext()){
			assertEquals(dIt.next(),inIt.next());
		}
		
	}
	
	@Test
	public void testPreOrden() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		datos = Arrays.asList(8,5,30,20,40);
		List<Integer> preO = arbol.preOrden();
		
		Iterator<Integer> dIt = datos.iterator();
		Iterator<Integer> preIt = preO.iterator();
		
		while(dIt.hasNext()){
			assertEquals(dIt.next(), preIt.next());
		}
		
	}
	
	@Test
	public void testPosOrden() {
		
		datos = Arrays.asList(8,20,5,40,30);
		arbol.addAll(datos);
		
		datos = Arrays.asList(5,20,40,30,8);
		List<Integer> posO = arbol.posOrden();
		
		Iterator<Integer> dIt = datos.iterator();
		Iterator<Integer> preIt = posO.iterator();
		
		while(dIt.hasNext()){
			assertEquals(dIt.next(), preIt.next());
		}
		
	}	
}
