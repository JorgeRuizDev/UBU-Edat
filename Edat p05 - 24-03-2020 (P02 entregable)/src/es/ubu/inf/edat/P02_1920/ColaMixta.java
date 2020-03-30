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
	private final int  tamañoNodo;

	protected class NodoMixto extends AbstractQueue<E> {

		/**
		 * Lista de elemmentos.
		 */
		private final List<E> contenido;
		/**
		 * Referencia al siguiente NODO.
		 */
		private NodoMixto siguiente;
		/**
		 * Referencia al Nodo anterior.
		 */
		private NodoMixto anterior;
		
		/**
		 * Constructor de la clase.
		 */
		public NodoMixto() {
			contenido = new ArrayList<E>(tamañoNodo);
		}

		/**
		 * Método booleano que permite añadir un elemento a la cola.
		 * Si el nodo está lleno, añade un nuevo nodo.
		 * @param e Elemento a añadir.
		 * @return siempre true.
		 */
		@Override
		public boolean offer(E e) {
			if(contenido.size()<tamañoNodo) {
				contenido.add(e);

				return true;
			} else
			return false;
		}

		/**
		 * Método booleano que extrae el primer elemento del nodo. Antes de devolverlo, lo elimina.
		 * Si el nodo está vacía devuelve null.
		 * @return elemento.
		 */
		@Override
		public E poll() {
			E devolver = null;
			devolver = peek();
			contenido.remove(0);
			return devolver;
		}

		/**
		 * Médoto que muestra el primer elemento del nodo, sin borrarlo.
		 * @return elemento.
		 */
		@Override
		public E peek() {
			
			if (contenido.size() <= 0)
				return null;
			
			return contenido.get(0);
		}

		/**
		 * Muestra el elemento que está en una posición del nodo.
		 * @param index posición del elemento.
		 * @return elemento de una posición. Null si dicha posición está vacía.
		 */
		public E peek (int index) {
			if (index < 0 || index >= tamañoNodo || contenido.size() == 0)
				return null;
			
			return contenido.get(index);
		}

		/**
		 * Iterador de la colección.
		 * @return el iterador correspondiente al nodo.
		 */
		@Override
		public Iterator<E> iterator() {
			return new IteradorNodo();
		}

		/**
		 * Devuelve el número de elementos qu contiene el nodo.
		 * @return entero que indica el tamaño.
		 */
		@Override
		public int size() {
			return contenido.size();
		}

		/**
		 * Iterador del nodo.
		 */
		private class IteradorNodo implements Iterator<E> {
			/**
			 * Entero que guarda el último elemento devuelto.
			 */
			private int punteroUltimaPos;

			/**
			 * Constructor del iterador.
			 */
			IteradorNodo(){
				this.punteroUltimaPos = 0;
			}

			/**
			 * Método que deuvelve si hay algún elemento restante por iterar en el nodo.
			 * @return booleano true o false.
			 */
			@Override
			public boolean hasNext() {
				return punteroUltimaPos < contenido.size();
			}

			/**
			 * Método que devuelve el siguiente método a iterear.
			 */
			@Override
			public E next() {
				if (this.hasNext()) {
					E elemento = contenido.get(punteroUltimaPos);
					punteroUltimaPos++;
					return elemento;
				}
				else
					return null;
			}	
			
		}

		/**
		 * Méotodo que devuelve el siguiente nodo al al actual.
		 * @return referencia al siguiente nodo en la cola, si no hay otro nodo, devuelve null.
		 */
		public NodoMixto getSiguienteNodo() {
			return siguiente;
		}

		/**
		 * Método que asigna el sisguiente nodo en la cola respescto al nodo actual.
		 * @param siguiente Siguiente nodo a referenciar.
		 */
		public void setSiguienteNodo(NodoMixto siguiente) {
			this.siguiente=siguiente;
		}

		/**
		 * Método permite actualizar el nodo anterior al que referencia este nodo.
		 * @param anterior nodo anterior.
		 */
		public void setAnteriorNodo(NodoMixto anterior) {
			this.anterior=anterior;
		}
		/**
		 * Método que devuelve el nodo anterior en la cola. Si no hay un nodo devuelve null (si es el primer nodo).
		 * @return referencia al nodo anterior.
		 */
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
	/**
	 * Método que muestra el primer elemento de la la cola.
	 * @return elemento.
	 */
	public E peek() {
		return primero.peek();
	}

	/**
	 * Método sobrecargado que permite obtener un elemento en cualquier posición.
	 * @param index Posición en la que queremos obtener el elemento.
	 * @return el elemento o Null si el índice está fuera de la cola.
	 */
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

	/**
	 * Método que devuelve el iterador de la cola.
	 * @return el iterador correspondiente a la cola.
	 */
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
	 * Métodos de control
	 */
	
	/**
	 * Método privado que nos indica si el último nodo de la cola está lleno.
	 * @return true o false.
	 */
	private boolean isUltimoNodoLleno() {
		return ultimo.size() >= this.tamañoNodo;
	}

	/**
	 * Método privado que nos indica si el último nodo está vacío
	 * @return true o false.
	 */
	private boolean isPrimerNodoVacío() {
		return primero.size() == 0;
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
	 * Método privado que permite eliminar un nodo y unir el resto para no romper la cadena.
	 * @param nodo Nodo a borrar,
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
	 */
	private class IteradorMixto implements Iterator<E> {
		/**
		 * Nodo que está siendo iterado
		 */
		NodoMixto nodoIterado;
		/**
		 * El iterador del nodo que está siendo iterado
		 */
		Iterator<E> itNodoIterado;

		/**
		 * Constructor del iterador.
		 */
		IteradorMixto(){
			nodoIterado = primero;
			itNodoIterado = primero.iterator();
		}

		/**
		 * Método que responde si quedan elementos en la cola a iterar.
		 * @return true si quedan, false si ya se han iterado todos.
		 */
		@Override
		public boolean hasNext() {
			
			if (itNodoIterado.hasNext()) { //Si el nodo actual tiene elementos
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

		/**
		 * Méotodo que devuelve el siugiente elemento a iterar.
		 * @return el siguiente elemento.
		 */
		@Override
		public E next() {
			if (this.hasNext())
				return itNodoIterado.next();
			else
				return null;
		}

	}
}
