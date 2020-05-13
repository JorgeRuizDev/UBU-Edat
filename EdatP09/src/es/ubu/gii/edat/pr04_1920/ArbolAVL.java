package es.ubu.gii.edat.pr04_1920;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolAVL<E> extends ArbolBB<E>{

	public ArbolAVL() {
		super();
	}

	@Override
	public boolean add(E e){
		// TODO - Sobreescribir para tener en cuenta el equilibrio del árbol
		super.add(e);
		return false;
	}
	

	@Override
	public boolean remove(Object o){
		// TODO - Sobreescribir para tener en cuenta el equilibrio del árbol
		return false;
	}

	////// Métodos solicitados que NO se incluyen en AbstractSet //////
	// (Son propios de los árboles)
	
	/**
	 * Devuelve la lista de elementos almacenados en el árbol en el
	 * orden en el que aparecerían en un recorrido en inorden
	 * 
	 * @return Lista con el contenido completo del arbol
	 */
	public List<E> inOrden() {
		// TODO - A completar por el alumno 
		// Solo se considerarán válidas las implementaciones ITERATIVAS
		return null;
	}

	/**
	 * Devuelve la lista de elementos almacenados en el árbol en el
	 * orden en el que aparecerían en un recorrido en preorden
	 * 
	 * @return Lista con el contenido completo del arbol
	 */
	public List<E> preOrden() {
		if (raiz == null) return null;

		List <E> orden = new LinkedList<>();
		Deque<Nodo> pila = new LinkedList<>();

		pila.add(raiz);

		//TODO: Que los tests no pasarán hasta hacer las rotaciones.
		//Está hecho con el pseudocódigo
		while (pila.size() >0){

			Nodo elemento = pila.pollLast();
		}

		return null;
	}

	//NO entiendo una mierda del pseudocódigo
	public Nodo rotacionSimpleIzquierda (Nodo nodoParam){
		Nodo izqNodoParam = nodoParam.getIzq();
		Nodo PadreNodoParam = buscar(this.raiz,nodoParam.getDato()).get(1);

		return null;
	}

	
	/**
	 * Devuelve la lista de elementos almacenados en el árbol en el
	 * orden en el que aparecerían en un recorrido en posorden
	 * 
	 * @return Lista con el contenido completo del arbol
	 */
	public List<E> posOrden() {
		// TODO - A completar por el alumno 
		// Solo se considerarán válidas las implementaciones ITERATIVAS
		return null;
	}
	
	/**
	 * Altura dentro del arbol a la que se encuentra insertado el nodo que contiene 
	 * un determinado dato pasado por parametro. En caso de que el dato no esté contenido
	 * en el árbol, se devuelve el valor -1.
	 *
	 * Altura: camino más largo nodo-hoja.
	 *
	 * @param dato sobre el que se quiere calcular la altura
	 * @return altura del nodo que contiene el dato (ver definición en teoria)
	 */
	public int altura(E dato){

		return altura(this.buscar(this.raiz,dato).get(0));
	}
	public int altura(Nodo dato){

		if (dato == null)
			return -1;

		int alturaIzq = this.altura(dato.getIzq());
		int alturaDer = this.altura(dato.getDer());

		if (alturaIzq > alturaDer)
			return 1 + alturaIzq;
		else
			return 1 + alturaDer;

	}

	/**
	 * Profundidad dentro del arbol a la que se encuentra insertado el nodo que contiene 
	 * un determinado dato pasado por parametro. En caso de que el dato no esté contenido
	 * en el árbol, se devuelve el valor -1.
	 *
	 * Profundidad: camino más largo raiz-nodo.
	 *
	 * @param dato sobre el que se quiere calcular la profundidad
	 * @return profundidad del nodo que contiene el dato (ver definición en teoria)
	 */
	public int profundidad(E dato){
		// TODO - A completar por el alumno
		return -1;
	}



	//TODO - Auxiliares RE-EQUILIBRADO
	// Metodos que permiten realizar el re-equilibrado del árbol
	private int alturaIzq(Nodo nodo){
		return altura(nodo.getIzq()) + 1;
	}

	private int alturaDer(Nodo nodo){
		return altura(nodo.getDer()) + 1;
	}

	/**
	 * Método auxiliar que calcula el factor de desequilibrio del árbol.
	 *
	 * Tipos de return:
	 * Positivo = El lado der es más largo.
	 * Negativo = El lado izq es más largo.
	 * 0 = Están equilibrados
	 * abs(return) > 1 = Hay un desequlibrio
	 * @return entero con signo.
	 */
	private int calcFactorDesequilibrio(){
		return this.alturaDer(raiz) - this.alturaIzq(raiz);
	}

	private boolean hayDesequlibrio(){
		return Math.abs(calcFactorDesequilibrio()) > 1;
	}
	// Se sugiere re-escribir el método de búsqueda del ArbolBB
	
}
