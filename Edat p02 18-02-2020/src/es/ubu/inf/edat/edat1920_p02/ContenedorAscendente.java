package es.ubu.inf.edat.edat1920_p02;

import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Colección que almacena elementos genéricos y permite compararlos e iterarlos.
 * 
 * @author Jorge Ruiz Gómez.
 * @param <E> elemento de la colección.
 */

// Se pide que esta clase extienda la clase AbstractCollection

/* TODO - Responder a la siguiente pregunta (como comentario Java es suficiente)
 * ¿Es necesario que nuestra clase implemente el interfaz Iterable<T>? 
 * Razona tu respuesta */

/*
 * Respuesta: 
 * No es necesario que se implemente en el contenedor/la colección, porque vamos
 * a devolver un objeto Iterador en .iterator().
 * 
 * Si que es necesario añadir dicho método, pero no implementar la interfaz, porque de dicha 
 * implementación se ocupará la clase del iterador que vamos a devolver con .iterator();
 * 
 * */
public class ContenedorAscendente<E> extends AbstractCollection<E>{

	// TODO - Se necesitarán incluir los atributos necesarios para almacenar los datos
	// que va a contener esta colección.
	// A completar por el alumno
	
	/**
	 * Array genérico donde se almacena el contenido de la coleeción.
	 */
	private E [] contenido;
	
	/**
	 * Comparador de la colección, es especificado en el constructor. 
	 */
	private Comparator <E> comparador;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param contenido Array con el contenido genérico.
	 */
	public ContenedorAscendente (E[] contenido){
		
		this.contenido = contenido;
		this.comparador = null;
	}

	/**
	 * Constructor de la clase.
	 * @param contenido Array con el contenido genérico.
	 * @param comparador Comparador de la clase.
	 */
	public ContenedorAscendente (E[] contenido, Comparator<E> comparador){
		
		this.contenido = contenido;
		this.comparador = comparador;
		
	}

	@SuppressWarnings("unchecked")
	/**
	 * Método privado para comparar dos objetos genéricos.
	 * @param el1 Primer objeto genérico.
	 * @param el2 Segundo objeto genérico.
	 * @return
	 */
	private int comparar(E el1, E el2){
		
		if (comparador == null)
			return ((Comparable <E>) el1).compareTo(el2);
		else
			return this.comparador.compare(el1, el2);
	}	
	


	@Override
	public Iterator<E> iterator() {
		
		return new IteradorAscendente();
	}

	@Override
	public int size() {
		
		return contenido.length;
	}
	
	
	
	
	/* TODO - Responder a la siguiente pregunta (como comentario Java es suficiente)
	 * ¿Será obligatorio implementar aqí métodos como:
	 * - int size() 
	 * - boolean isEmpty()
	 * que ya conocemos que tienen todas las colecciones? 
	 * ¿Por qué? Razona tu respuesta.	 */
	
	/*
	 * No es necesario, porque size ya está implementado en la clase de arriab, y isEmpty puede ser últil, 
	 * pero los statemens:
	 * 
	 * if (isEmpty()) e if (size() == 0) darían exactamente el mismo resultado, sólo que el primero
	 * mejora la estructura y legibilidad del código.
	 * */
	
	/**
	 * Iterador que siempre devuelve los elementos mayores.
	 * @author Jorge Ruiz Gómez.
	 *
	 */
	private class IteradorAscendente  implements Iterator<E> {

		/**
		 * El útlimo objeto encontrado/devuelto por la función next.
		 * Lo guardamos para siempre tener el más grande.
		 * @see next()
		 */
		private E ultimoElementoEncontrado;
		
		/**
		 * Última posición del último objeto devuelto en el Array.
		 * Por defecto es -1.
		 */
		private int ultimaPosiciónDeElemento;
		/**
		 * Número de elementos que van a ser devueltos como máximo por la función Next.
		 * @see next
		 */
		private int numeroElementosADevolver;
		/**
		 * Número de elementos Encontrados/Devueltos por el iterador hasta el momento.
		 * Tiene como valor máximo numeroElemetnosADevolver, antes de que hasNext deje de funcionar.
		 * 
		 * @see numeroElementosADevolver
		 * @see hasNext
		 */
		private int numeroElementosEncontrados;
		
		/**
		 * Constructor de la clase.
		 */
		IteradorAscendente(){
			
			this.ultimaPosiciónDeElemento = -1;
			numeroElementosADevolver = this.calcularNúmeroElementosADevolver();

		}
		
		/**
		 * Función que calcula el número de elementos máximo que puede devolver el iterador.
		 * Siempre se devuelve el primer elemento, y aquellos elementos que sean mayores que el último elemento devuelto.
		 * Así se devuelve una lista en la que todos los elementos son ascendentes, eliminando los que son menores que el último.
		 * 
		 * @return Entero con el número de valores a devolver.
		 */
		private int calcularNúmeroElementosADevolver() {
			
			int nElementosMayores = 0;
			E elementoMasGrande = contenido[0];
			
			for (E elemento : contenido) {
				
				if (comparar(elemento, elementoMasGrande) > 0) {
					elementoMasGrande = elemento;
					nElementosMayores ++;
				}
			}
			this.numeroElementosADevolver = nElementosMayores;
			
			return nElementosMayores + 1;
		}
		

		
		@Override
		public boolean hasNext() {
			return (this.numeroElementosADevolver > numeroElementosEncontrados);
		}

		@Override
		public E next() {

			if (this.hasNext()) {
				
				//Devolución del primer valor.
				if (this.ultimaPosiciónDeElemento == -1) {
					this.ultimaPosiciónDeElemento = 0;
					this.numeroElementosEncontrados = 1;
					this.ultimoElementoEncontrado = contenido[0];
					return contenido[0];
				}
				
				//Devolución de los valores que son mayores que el último valor devuelto.
				for (int i = this.ultimaPosiciónDeElemento; i< size(); i++) {
					
					if (comparar(contenido[i],this.ultimoElementoEncontrado) > 0) {
						
						this.ultimaPosiciónDeElemento = i;
						this.numeroElementosEncontrados++;
						this.ultimoElementoEncontrado = contenido[i];
						return contenido[i];
					}
				}// fin for
			}
			return null;	
		}
	}
}