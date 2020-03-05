package es.ubu.inf.edat.P01_1920;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.ubu.inf.edat.P01_1920.data.DataLoader;

public class ThreeSumCalculationTest {

	DataLoader in;
	int[] a;

	@BeforeEach
	void setUp() throws Exception {
		in = new DataLoader();
	}

	@AfterEach
	void tearDown() throws Exception {
		in.close();
	}

	/**
	 * 
	 */
	@Test
	void ejemploEnunciado_random(){

		int[] array = {5, 3, -8, -2, 20, 10, -12};

		int [][] result = ThreeSumCalculation.findTriplets_random(array);

		for (int[] tuple : result)
			assertEquals(0, tuple[0]+tuple[1]+tuple[2]);

		assertEquals(3,result.length);

		if(distincts(result) != result.length)
			fail("There are duplicated tuples among the results.");

	}

	/**
	 * 
	 */
	@Test
	void ejemploEnunciado_ordered(){

		int[] array = {5, 3, -8, -2, 20, 10, -12};

		int [][] result = ThreeSumCalculation.findTriplets_ordered(array);

		for (int[] tuple : result)
			assertEquals(0, tuple[0]+tuple[1]+tuple[2]);

		assertEquals(3, result.length);

		if(distincts(result) != result.length)
			fail("There are duplicated tuples among the results.");

	}

	/**
	 * 
	 */
	@Test
	void test_1K_rand() {

		in.LoadFile("./data/1Kints.txt");
		a = in.readAllInts();

		int[][] result = ThreeSumCalculation.findTriplets_random(a);

		assertEquals(70,result.length);

		for (int[] tuple : result)
			assertEquals(0, tuple[0]+tuple[1]+tuple[2]);

		if(distincts(result) != result.length)
			fail("There are duplicated tuples among the results.");

	}

	/**
	 * 
	 */
	@Test
	void test_1K_ord() {

		in.LoadFile("./data/1Kints.txt");
		a = in.readAllInts();

		int[][] result = ThreeSumCalculation.findTriplets_ordered(a);

		assertEquals(70,result.length);

		for (int[] tuple : result)
			assertEquals(0, tuple[0]+tuple[1]+tuple[2]);

		if(distincts(result) != result.length)
			fail("There are duplicated tuples among the results.");

	}

	/**
	 * 
	 */
	@Test
	void test_2K_rand() {

		in.LoadFile("./data/2Kints.txt");
		a = in.readAllInts();

		int[][] result = ThreeSumCalculation.findTriplets_random(a);

		assertEquals(528,result.length);

		for (int[] tuple : result)
			assertEquals(0, tuple[0]+tuple[1]+tuple[2]);

		if(distincts(result) != result.length)
			fail("There are duplicated tuples among the results.");

	}

	/**
	 * 
	 */
	@Test
	void test_2K_ord() {

		in.LoadFile("./data/2Kints.txt");
		a = in.readAllInts();

		int[][] result = ThreeSumCalculation.findTriplets_ordered(a);

		assertEquals(528, result.length);

		for (int[] tuple : result)
			assertEquals(0, tuple[0]+tuple[1]+tuple[2]);


		if(distincts(result) != result.length)
			fail("There are duplicated tuples among the results.");

	}	

	/**
	 *  Test to checks that, when computing the Three-sum in an array using the ordered method,
	 *  the original array is left unchanged
	 */
	@Test
	void arrayNoModificado() {

		int[] array = {5, 3, -8, -2, 20, 10, -12};
		int[] copy = new int[array.length];

		for(int i=0; i<array.length; i++)
			copy[i] = array[i];

		int [][] result = ThreeSumCalculation.findTriplets_ordered(array);

		for(int i=0; i<array.length; i++)
			assertEquals(array[i], copy[i]);

	}

	@Test
	void busquedaBinaria_contenido() {
		
		int[] array = {5, 3, -8, -2, 20, 10, -12};
		
		Arrays.sort(array);
		
		assertEquals(2, ThreeSumCalculation.busquedaBinaria(array,-2));
		
	}

	@Test
	void busquedaBinaria_NoContenido() {
		
		int[] array = {5, 3, -8, -2, 20, 10, -12};
		
		Arrays.sort(array);
		
		assertTrue(0 > ThreeSumCalculation.busquedaBinaria(array,8));
		
	}

	
	///////////////////////////////////////////////////////////////////////////

	/**
	 * Calculates the number of distinct sub arrays there are in an array of int arrays 
	 * 
	 * @param array
	 * @return
	 */
	protected int distincts(int[][] array) {

		int eq = 0;

		// Comprueba cada par de sub-arrays
		for (int i=0; i<array.length; i++) {
			for (int j=i+1; j<array.length; j++) {

				// En caso de que sean nulos ambos, se cuenta como repetición
				if(array[i] == null && array[j] == null) {
					eq++; continue;
				}

				// Primero se comprueba la longitud
				if(array[i].length != array[j].length) {
					continue;
				}

				// Luego se comprueba el contenido de ambos arrays
				boolean same = true;
				for (int k=0; k<array[i].length; k++) {
					if (array[i][k] != array[j][k]) {
						same = false; break;
					}
				}
				if (same) eq++;

			}
		}
		// El numero de distintos será el inverso de los iguales
		return array.length - eq;
	}

}
