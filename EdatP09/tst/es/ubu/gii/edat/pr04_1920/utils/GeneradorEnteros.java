package es.ubu.gii.edat.pr04_1920.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.Random;

public class GeneradorEnteros {

	static Random rand = new Random();
	
	/**
	 * Genera numeros aleatorios entre -100 y 100. Incluye numeros negativos.
	 * 
	 * @param tamano 
	 * @return
	 */
	public static List<Integer> listaAleatoria (int tamano){
		
		Integer[] aleatoria = new Integer[tamano];
		
		for(int i=0; i<tamano; i++){
			
			int aleat = rand.nextInt(tamano);
			if(rand.nextBoolean())
				aleat = -1 * aleat;
			
			aleatoria[i] = aleat;
			
		}
		
		return Arrays.asList(aleatoria);
		
	}

	/**
	 * Devuelve una lista de numeros aleatorios. Los numeros perteneceran siempre
	 * al rango delimitado por los limites inferior y superior. 
	 * 
	 * @param tamano
	 * @param inferior
	 * @param superior
	 * @return
	 */
	public static List<Integer> listaAleatoria (int tamano, int inferior, int superior){
		
		Integer[] aleatorio = new Integer[tamano];
		for(int i=0; i<tamano; i++){
			aleatorio[i] = (int)(Math.random()*(superior-inferior));
			aleatorio[i] = aleatorio[i] + inferior; 
		}

		return Arrays.asList(aleatorio);
		
	}

	/**
	 * Genera una lista aleatoria seleccionando solo de entre los elementos indicados
	 * en la lista pasada como parametro.
	 * 
	 * @param tamano
	 * @param seleccionables: lista de los elementos que se pueden seleccionar
	 * @return
	 */
	public static <E> List<E> listaAleatoria (int tamano, List<E> seleccionables){
		
		List<E> seleccionados = new ArrayList<>(tamano);
		for(int i=0; i<tamano; i++){
			E elem = seleccionables.get( rand.nextInt(seleccionables.size()) );
			seleccionados.add(elem); 
		}
		return seleccionados;
	}
	
	/**
	 * 
	 * @param tamano
	 * @param repetidos
	 * @return
	 */
	public static List<Integer> listaAleatoria (int tamano, float repetidos){
		
		Integer[] aleatorio = new Integer[tamano];

		aleatorio[0] = rand.nextInt();
		
		for(int i=1; i<tamano; i++){
			
			if(Math.random() < repetidos){
				aleatorio[i] = aleatorio[i-1];
				continue;
			}
					
			
			int aleat = rand.nextInt(tamano);
			if(rand.nextBoolean())
				aleat = -1 * aleat;
			
			aleatorio[i] = aleat;

		}
		
		return Arrays.asList(aleatorio);

	}
	
	/**
	 * 	
	 * @param tamano
	 * @return
	 */
	public static List<Integer> listaAleatoriaUnicos (int tamano){
		
		List<Integer> lista = new ArrayList<>(tamano);
		
		for(int i=0; i<tamano*1.5; i++) {
			lista.add(i);
		}

		Collections.shuffle(lista);
		return lista.subList(0, tamano);
		
	}
	
	/**
	 * Genera numeros aleatorios entre -100 y 100. Incluye numeros negativos.
	 * 
	 * @param tamano
	 * @return
	 */

	public static int[] arrayAleatorio(int tamano){
		
		List<Integer> lista = listaAleatoria (tamano);
		int[] aleatoria = new int[tamano];
		
		for(int i=0; i<tamano;i++){
			aleatoria[i] = lista.get(i);
		}
		
		return aleatoria;
		
	}
	
	/**
	 * 
	 * @param tamano
	 * @param min
	 * @param max
	 * @return
	 */
	public static int[] arrayAleatorio(int tamano, int min, int max){
		
		List<Integer> lista = listaAleatoria (tamano, min, max);
		int[] aleatoria = new int[tamano];
		
		for(int i=0; i<tamano;i++){
			aleatoria[i] = lista.get(i);
		}
		
		return aleatoria;
	}


	
	public static <E> String toString (int[] array){
		String s = "[" + array[0];
		for(int i=1; i<array.length; i++){
			s = s + ", " + array[i];
		}
		s = s + "]";
		return s;
	}
	
}
