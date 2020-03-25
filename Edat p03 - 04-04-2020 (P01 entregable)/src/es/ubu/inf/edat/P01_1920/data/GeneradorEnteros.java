package es.ubu.inf.edat.P01_1920.data;

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
	 * Genera numeros aleatorios entre -tamano y tamano. Incluye numeros negativos.
	 * 
	 * @param tamano integer del intervalo a genera y tamaño de la lista.
	 * @return lista con los ints.
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
	 * @param tamano tamaño de la lista.
	 * @param inferior límite máximo.
	 * @param superior límite mínimo.
	 * @return lista aleatoria con los enteros generados.
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
	 * @param <E> elemento.
	 * @param tamano tamaño de la lista.
	 * @param seleccionables: lista de los elementos que se pueden seleccionar.
	 * @return lista aleatoria de enteros.
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
	 * Méodo publico que genera lista aleatoria.
	 * @param tamano tamaño de la lista a generar.
	 * @param repetidos float con la diferencia mínima para tratar de repetido.
	 * @return lista aleatoria. 	
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
	 * Genera una lista aleatoria de números únicos (sin ningun elemento repetido).	
	 * 
	 * @param tamano tamaño de la lista.
	 * @return lista de aleatorios unicos.
	 */
	public static List<Integer> listaAleatoriaUnicos (int tamano){
		
		List<Integer> lista = new ArrayList<>(tamano);
		
		for(int i=0; i<tamano*1.5; i++) {
			if (Math.random()<0.5)
				lista.add(-i);
			else
				lista.add(i);
		}

		Collections.shuffle(lista);
		return lista.subList(0, tamano);
		
	}
	
	/**
	 * Genera numeros aleatorios entre -100 y 100. Incluye numeros negativos.
	 * 
	 * @param tamano tamaño de la lista.
	 * @return lista aleatoria.
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
	 * @param tamano tamaño de la lista.
	 * @param min entero mínimo.
	 * @param max entero máximo.
	 * @return lista aleatoria.
	 */
	public static int[] arrayAleatorio(int tamano, int min, int max){
		
		List<Integer> lista = listaAleatoria (tamano, min, max);
		int[] aleatoria = new int[tamano];
		
		for(int i=0; i<tamano;i++){
			aleatoria[i] = lista.get(i);
		}
		
		return aleatoria;
	}

	
	/**
	 * Método público que genera una lista de números aleatorios sin repetir.
	 * @param tamano tamaño del array a generar.
	 * @return array con números aleatorios.
	 */
	public static int[] arrayAleatorioUnicos(int tamano){
		
		List<Integer> lista = listaAleatoriaUnicos (tamano);
		int[] aleatoria = new int[tamano];
		
		for(int i=0; i<tamano;i++){
			aleatoria[i] = lista.get(i);
		}
		
		return aleatoria;
		
	}
	
	/**
	 * Método que convierte un array a texto.
	 * @param <E> Elemento.
	 * @param array array a convertir.
	 * @return string con el array separado con comas.
	 */
	public static <E> String toString (int[] array){
		String s = "[" + array[0];
		for(int i=1; i<array.length; i++){
			s = s + ", " + array[i];
		}
		s = s + "]";
		return s;
	}
	
}
