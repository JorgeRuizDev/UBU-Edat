package es.ubu.inf.edat.edat1920_p01;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import es.ubu.inf.edat.edat1920_p01.datos.*;
import es.ubu.inf.edat.edat1920_p01.GestorMatriculados_M;



/*
 * TODO:
 * implmenetar el método equals en moto
 * Dos motos son iguales si su MARCA y MODELO son iguales.
 * 
 * 
 */
public class GestorMatriculados_M_Test {

	private GestorMatriculados_M gestor = new GestorMatriculados_M();

	/**
	 *  DEBEN DE COMENTAR
	 */
	@Test
	public void testInsertar() {

		Vehiculo[] nuevos = GeneradorVehiculos.todos();
		assertEquals("El numero de vehiculos de mas de cuatro ruedas debe ser 12", 12, gestor.cargaListado(nuevos));
		
	}

	/**
	 * 
	 */
	@Test
	public void testIterador() {

		Vehiculo[] nuevos = GeneradorVehiculos.todos();
		gestor.cargaListado(nuevos);
		
		Iterator<Moto> it = gestor.iterator();
		int recuperadas = 0;
		
		while(it.hasNext()){
			Vehiculo m = it.next();
			System.out.println(m);
			assertTrue(m instanceof Moto);
			recuperadas++;
		}
		
		assertEquals("El numero de motos recuperados debe ser 3, recuperados: ", 3, recuperadas);
		
		gestor.vaciaListado();
		Vehiculo[] motosCargadas = GeneradorVehiculos.soloMotos();

		int cuantosHay = gestor.cargaListado(motosCargadas);
		
		assertEquals("El numero de vehiculos cargado debe ser 3: ", motosCargadas.length, cuantosHay);
		
		int posicion = motosCargadas.length-1;
		
		it = gestor.iterator();
		while(it.hasNext()){
			Vehiculo m = it.next();
			assertTrue(m==motosCargadas[posicion]);
			posicion--;
		}
		
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testMetodoEquals() {

		Vehiculo[] nuevos = GeneradorVehiculos.soloMotos();
	
		Moto moto1 = (Moto) nuevos[0];
		Moto moto2 = (Moto) nuevos[1];
		
		assertTrue ("Comprobamos que se trata del mismo objeto ",moto1==nuevos[0]);
		assertFalse ("Comprobamos que no son el mismo objeto ", moto1==moto2);

		assertFalse ("Comprobamos que su marca y modelo no son iguales ",moto1.equals(moto2));
		moto2.setMarca(moto1.getMarca());
		moto2.setModelo(moto1.getModelo());
		assertTrue ("Comprobamos que su marca y modelos son los mismos", moto1.equals(moto2));
		
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testVacio() {
		
		Iterator<Moto> it = gestor.iterator();
		int recuperadas = 0;
		
		while(it.hasNext()){
			recuperadas++;
		}
		assertEquals("No se deberia recuperar ningun vehiculo de tipo moto, al no haberlos insertado", 0, recuperadas);
	}
	
}
