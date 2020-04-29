package es.ubu.gii.edat.pr04_1920;

import java.util.List;

public class ArbolAVL<E> extends ArbolBB<E>{

	public ArbolAVL() {
		super();
	}

	@Override
	public boolean add(E e){
		// TODO - Sobreescribir para tener en cuenta el equilibrio del árbol		
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
		// TODO - A completar por el alumno 
		// Solo se considerarán válidas las implementaciones ITERATIVAS
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
	 * @param dato sobre el que se quiere calcular la altura
	 * @return altura del nodo que contiene el dato (ver definición en teoria)
	 */
	public int altura(E dato){
		// TODO - A completar por el alumno 
		return -1;
	}

	/**
	 * Profundidad dentro del arbol a la que se encuentra insertado el nodo que contiene 
	 * un determinado dato pasado por parametro. En caso de que el dato no esté contenido
	 * en el árbol, se devuelve el valor -1.
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

	// Se sugiere re-escribir el método de búsqueda del ArbolBB
	
}
