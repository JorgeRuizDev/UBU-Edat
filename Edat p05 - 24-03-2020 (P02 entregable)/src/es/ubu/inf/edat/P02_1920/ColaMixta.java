package es.ubu.inf.edat.P02_1920;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColaMixta<E> extends AbstractQueue<E> {
	
	/*
	 * Referencia al primer nodo de la cola.
	 */
	private NodoMixto primero;
	/*
	 * Referencia al último nodo de la cola.
	 */
	private NodoMixto ultimo;
	/*
	 * Valor que indica el tamaño de cada nodo.
	 */
	private int tamañoNodo;

	protected class NodoMixto extends AbstractQueue<E> {

		/*
		 * Lista de elemmentos.
		 */
		private List<E> contenido;
		/*
		 * Referencia al siguiente NODO.
		 */
		private NodoMixto siguiente;
		/*
		 * Referencia al Nodo anterior.
		 */
		private NodoMixto anterior;
		
		/*
		 * Constructor de la calse.
		 */
		public NodoMixto() {
			contenido = new ArrayList<E>(tamañoNodo);
		}
		
		@Override
		public boolean offer(E e) {
			if(contenido.size()<tamañoNodo) {
				contenido.add(e);

				return true;
			} else
			return false;
		}
		@Override
		public E poll() {
			E devolver = null;
			devolver = peek();
			contenido.remove(0);
			return devolver;
		}
		@Override
		public E peek() {
			
			if (contenido.size() <= 0)
				return null;
			
			return contenido.get(0);
		}
		
		public E peek (int index) {
			if (index < 0 || index >= tamañoNodo || contenido.size() == 0)
				return null;
			
			return contenido.get(index);
		}
		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return new IteradorNodo();
		}
		@Override
		public int size() {
			return contenido.size();
		}
		
		private class IteradorNodo implements Iterator<E> {
			int punteroUltimaPos;
			
			IteradorNodo(){
				this.punteroUltimaPos = 0;
			}
			@Override
			public boolean hasNext() {
				if (punteroUltimaPos < contenido.size())
					return true;
				else
					return false;
			}

			@Override
			public E next() {
				E elemento = contenido.get(punteroUltimaPos);
				punteroUltimaPos++;
				return elemento;
			}	
			
		}
		
		public NodoMixto getSiguienteNodo() {
			return siguiente;
		}
		
		public void setSiguienteNodo(NodoMixto siguiente) {
			this.siguiente=siguiente;
		
		}
		
		public void setAnteriorNodo(NodoMixto anterior) {
			this.anterior=anterior;
		}
		public NodoMixto getAnteriorNodo() {
			return anterior;
		}
		

	}

	
	

	/**
	 * Constructor de la clase
	 * 
	 * @param tamañoNodo Numero de elementos que se introducen como maximo en un
	 *         nodo.
	 */
	public ColaMixta(int tamañoNodo) {
		this.tamañoNodo=tamañoNodo;
		primero=new NodoMixto();
		ultimo = primero;
	}

	
	/**
	 * Método privado que permite añadir más nodos a la cola una vez las colas están llenas
	 * 
	 * @return estado final: True -> Se ha podido añadir el nodo. False -> La última cola no está llena por lo que no puede ser cambiada.
	 */

	@Override
	public boolean offer(E e) {
		if (!isUltimoNodoLleno()) {
			ultimo.offer(e);
			return true;
		} else if (isUltimoNodoLleno()) {
			añadirNodoVacíoEnLaCola();
			ultimo.offer(e);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Devuelve el primer elemento de la lista.
	 * Es decir, devuelve el primer elemento del primer nodo.
	 */
	@Override
	public E poll() {
		
		if(isPrimerNodoVacío() && primero == ultimo) {
			return null;

		}else if (isPrimerNodoVacío() )
			eliminarNodo(primero);
		
		
		return primero.poll();
	}

	@Override
	public E peek() {
		return primero.peek();
	}
	
	public E peek (int index) {
		
		
		NodoMixto nodo = primero; // Nodo en el que se encuentra el elemento
		int nNodo = 0;		// El nº de nodo en el que se encuentra
		int posicionEnNodo = -1;	//La posición en en la que se encuentra dentro del nodo.
		
		
		if ( index  % tamañoNodo == 0) 			//Traducimos el índice a nodo y índice de dicho nodo
			nNodo = index / tamañoNodo;
		else
			nNodo = (index / tamañoNodo) + 1; 
		
		posicionEnNodo = nNodo * tamañoNodo - index ;
		
		
		//Obtenemos el nodo
		for (int i= 1; i <= nNodo; i++) {
			
			if (nodo.getSiguienteNodo() == null) // hemos llegado al final del nodo
				break;
				
			nodo = nodo.getSiguienteNodo();
		}
		
		//obtenemos la posición
		return nodo.peek(posicionEnNodo);
	}

	@Override
	public Iterator<E> iterator() {
		
		
		
		
		return new IteradorMixto();
	}

	/*
	 * Método público que nos devuelve el tamaño de la cola implementada.
	 * Es decir, la suma del tamaño de cada uno de los nodos.
	 * Es decirl, el número de elementos en la cola.
	 */
	@Override
	public int size() {
		
		boolean flag = true;
		
		int contadorTamaño=0;
		
		NodoMixto siguienteNodo = primero;
		
		contadorTamaño+=siguienteNodo.size();
		int nNodos=1;
		while(flag) {
			
			if(siguienteNodo.getSiguienteNodo()!=null) {
				siguienteNodo=siguienteNodo.getSiguienteNodo();
				contadorTamaño+=siguienteNodo.size();
				nNodos++;
			}else {
				flag = false;
			}
		}
		return contadorTamaño;	
		
	}
	
	/*
	 * 
	 * Métodos de control
	 * 
	 */
	
	/**
	 * Método privado que nos indica si el último nodo de la cola está lleno.
	 * @return
	 */
	private boolean isUltimoNodoLleno() {
		if (ultimo.size() < this.tamañoNodo) 
			return false;
		else 
			return true;
	}
	/**
	 * Método privado que nos indica si el último nodo está vacío
	 * @return
	 */
	private boolean isUltimoNodoVacío() {
		if (ultimo.size() == 0) 
			return true;
		else 
			return false;
		
	}
	/**
	 * Método privado que nos indica si el último nodo de la cola está lleno.
	 * @return
	 */
	private boolean isPrimerNodoLleno() {
		if (primero.size() < this.tamañoNodo) 
			return false;
		else 
			return true;
	}
	/**
	 * Método privado que nos indica si el último nodo está vacío
	 * @return
	 */
	private boolean isPrimerNodoVacío() {
		if (primero.size() == 0) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Método privado que permite añadir un nuevo nodo a la cola.
	 * 
	 * @return si el nodo anterior tiene elementos, no se realizan cambios y devuleve false.
	 */
	private boolean añadirNodoVacíoEnLaCola() {
		
		if (!isUltimoNodoLleno()) {
			System.err.println("Se ha intentado añadir un nuevo nodo y el último nodo no está lleno");
			return false;
		}
		assert ultimo.getSiguienteNodo() == null : "El último nodo no puede referencia a otro nodo como siguiente en una estructura lineal";

		
		NodoMixto nuevoNodo = new NodoMixto();
		NodoMixto antiguoUltimo = ultimo;
		antiguoUltimo.setSiguienteNodo(nuevoNodo);
		ultimo = nuevoNodo;
		ultimo.setAnteriorNodo(antiguoUltimo);
		
		return true;
	}
	
	/**
	 * Método privado que permite eliminar un nodo y unir el resto.
	 * 
	 * @return true si se han realizado cambios y false si no.
	 */
	private boolean eliminarNodo(NodoMixto nodo) {
		
		if (nodo.size() != 0 ) {
			System.err.println("No se puede borrar un nodo que no está vacío. Número de elementos: " + this.size());
			return false;
		}
		
		NodoMixto nodoAnterior = nodo.getAnteriorNodo();
		NodoMixto nodoSiguiente = nodo.getSiguienteNodo();
		
		if (nodoAnterior != null && nodoSiguiente != null) {
			System.err.println("No se puede borrar el único nodo exitente, o este nodo no se encuentra en la cadena!");
			return false;
		}
		
		if ( nodoAnterior != null ) //si nodo no es el PRIMER nodo
			nodoAnterior.setSiguienteNodo(nodoSiguiente);
		else
			this.primero=nodoSiguiente;
			
		if ( nodoSiguiente != null ) //si nodo no es el ÚLTIMO nodo
			nodoSiguiente.setAnteriorNodo(nodoAnterior);
		else
			this.ultimo=nodoAnterior;
		
		return true;
	}
	
	/**
	 * Iterador que permite recorrer todos los elementos de la cola. Debe recorrer
	 * cada segmento de la misma (empleando el iterador del nodo) y pasar al nodo
	 * siguiente para repetir la operación. Se detiene al no haber más nodos.
	 *
	 * @param <E>
	 */
	private class IteradorMixto implements Iterator<E> {
		
		NodoMixto nodoIterado;
		Iterator<E> itNodoIterado;
		
		IteradorMixto(){
			nodoIterado = primero;
			itNodoIterado = primero.iterator();
		}
		
		@Override
		public boolean hasNext() {
			
			if (itNodoIterado.hasNext()) {
				return true;
			}
			else if (nodoIterado.getSiguienteNodo() != null){ //si hay más nodos
				
				nodoIterado = nodoIterado.getSiguienteNodo();
				itNodoIterado = nodoIterado.iterator();
				
				return itNodoIterado.hasNext();
			}else {
				return false;
			}
			
			
		}

		@Override
		public E next() {
			if (this.hasNext())
				return itNodoIterado.next();
			else
				return null;
		}

	}
}
