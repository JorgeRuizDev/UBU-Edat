package es.ubu.gii.edat.pr04_1920;

import java.util.*;

public class ArbolAVL<E> extends ArbolBB<E>{

	public ArbolAVL() {
		super();
	}

	@Override
	public boolean add(E e){

		boolean devolver = super.add(e);

		if (devolver){

			boolean hayNodosSuperiores = true;
			do{
				Nodo nodoAnterior = super.buscar(super.raiz,e).get(1);
				if(nodoAnterior==null) {
					hayNodosSuperiores = false;
				}
				else {
					reequilibrioAVL(nodoAnterior);
					e = nodoAnterior.getDato();
				}
			} while(hayNodosSuperiores);
		}

		return devolver;
	}
	

	@Override
	public boolean remove(Object o){


		E datoBorrar;

		try{
			datoBorrar = (E) o;
		}catch(ClassCastException cce){
			return false;
		}

		boolean hayCambios = super.remove(o);
		if (hayCambios){

			boolean hayNodosSuperiores = true;
			do{
				Nodo nodoAnterior = super.buscar(super.raiz,datoBorrar).get(1);
				if(nodoAnterior==null) {
					hayNodosSuperiores = false;
				}
				else {
					reequilibrioAVL(nodoAnterior);
					datoBorrar = nodoAnterior.getDato();
				}
			} while(hayNodosSuperiores);
		}
		return (hayCambios);
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
		if (raiz == null) return null;

		List <E> orden = new LinkedList<>();
		Deque <Nodo> pila = new LinkedList<>();

		pila.offerLast(raiz);
		Nodo nodoPrincipal = raiz;

		//Estructura auxiliar que almacena el número de accesos de un nodo


		while (! pila.isEmpty()){

			if (nodoPrincipal != null){
				pila.offerLast(nodoPrincipal);
				nodoPrincipal = nodoPrincipal.getIzq();
			}
			else{
				orden.add(pila.peekLast().getDato());
				nodoPrincipal = pila.pollLast().getDer();
			}
		}

		return orden;

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


		//Está hecho con el pseudocódigo
		while (! pila.isEmpty()){

			Nodo nodoActual = pila.pollLast();
			orden.add(nodoActual.getDato());

			//primero el lado izquierdo
			if (nodoActual.getDer() != null)
				pila.add(nodoActual.getDer());

			//luego el lado derecho
			if (nodoActual.getIzq() != null)
				pila.add(nodoActual.getIzq());


		}

		return orden;
	}

	/**
	 * Devuelve la lista de elementos almacenados en el árbol en el
	 * orden en el que aparecerían en un recorrido en posorden
	 * 
	 * @return Lista con el contenido completo del arbol
	 */
	public List<E> posOrden() {
		if (raiz == null) return null;

		List <E> orden = new LinkedList<>();
		Deque<Nodo> pila = new LinkedList<>();

		pila.add(raiz);

		HashMap <Nodo,Nodo> elementosIzquierda = new HashMap<>();
		HashMap <Nodo,Nodo> elementosDerecha = new HashMap<>();

		Nodo nodoPrincipal = raiz;


		while (!pila.isEmpty()){

			//Vamos hacia la izquierda
			if (!elementosIzquierda.containsKey(nodoPrincipal)){
				pila.offerLast(nodoPrincipal);
				elementosIzquierda.put(nodoPrincipal,nodoPrincipal);

				nodoPrincipal = nodoPrincipal.getIzq();

				//Si la izquierda está vacía, retrocedemos
				if(nodoPrincipal == null)
					nodoPrincipal = pila.pollLast();
			}
			//Vamos hacia la derecha una vez hemos ido hacia la izquierda
			if (elementosIzquierda.containsKey(nodoPrincipal) &&
					!elementosDerecha.containsKey(nodoPrincipal)){
				pila.offerLast(nodoPrincipal);
				elementosDerecha.put(nodoPrincipal,nodoPrincipal);

				nodoPrincipal = nodoPrincipal.getDer();

				//Si la derecha está vacía, retrocedemos
				if(nodoPrincipal == null)
					nodoPrincipal = pila.pollLast();
			}

			//Si ya se han visitado ambos lados de un nodo, se saca su valor
			if (elementosIzquierda.containsKey(nodoPrincipal) &&
					elementosDerecha.containsKey(nodoPrincipal)){
				assert nodoPrincipal != null;
				orden.add(nodoPrincipal.getDato());
				nodoPrincipal=pila.pollLast();
			}
		}

		return orden;
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
		if (dato.getIzq()==null & dato.getDer()==null){
			return 0;
		}

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

		//Nos posicionamos en la raiz
		Nodo nodoActual = this.raiz;
		int profundidad = 0;

		//Mientras no estemos en el nodo con el dato, bajamos un puesto y sumamos uno a la profundidad
		while (nodoActual.getDato() != dato){
			profundidad++;
			if(super.comparar(nodoActual.getDato(),dato)>0){
				nodoActual = nodoActual.getIzq();
			} else {
				nodoActual = nodoActual.getDer();
			}
			//Si nos hemos salido del arbol y no hemos encontrado el elemento, devolvemos -1
			if(nodoActual == null){
				return -1;
			}
		}
		return profundidad;
	}



	//TODO - Auxiliares RE-EQUILIBRADO
	// Metodos que permiten realizar el re-equilibrado del árbol
	private int alturaIzq(Nodo nodo){
		if(nodo.getIzq()==null){
			return 0;
		}
		return altura(nodo.getIzq()) + 1;
	}

	private int alturaDer(Nodo nodo){
		if(nodo.getDer()==null){
			return 0;
		}
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
	private int calcFactorDesequilibrio(Nodo raiz){
		return this.alturaDer(raiz) - this.alturaIzq(raiz);
	}



	// Se sugiere re-escribir el método de búsqueda del ArbolBB

	/**
	 * Metodo que rota un subArbol a la izquierda, dada la
	 * raiz de dicho subArbol
	 *
	 * @param nodoDesbalanceado Raiz del subArbol desbalanceado
	*/
	private void rotacionIzquierda(Nodo nodoDesbalanceado){
		List<Nodo> lista = super.buscar(super.raiz,nodoDesbalanceado.getDato());
		Nodo nodoSuperior = lista.get(1);
		Nodo nodoPivote = nodoDesbalanceado.getDer();
		Nodo auxiliarIPivote = nodoPivote.getIzq();

		//Hacemos el balanceo
		nodoPivote.setIzq(nodoDesbalanceado);
		nodoDesbalanceado.setDer(auxiliarIPivote);

		//Cambiamos el nodo que apuntaba al desbalanceado
		if(nodoSuperior.getIzq().equals(nodoDesbalanceado)){
			nodoSuperior.setIzq(nodoPivote);
		} else
			nodoSuperior.setDer(nodoPivote);


	}

	/**
	 * Metodo que rota un subArbol a la derecha, dada la
	 * raiz de dicho subArbol
	 *
	 * @param nodoDesbalanceado Raiz del subArbol desbalanceado
	 */
	private void rotacionDerecha(Nodo nodoDesbalanceado){
		Nodo nodoSuperior = this.nodoAnterior(nodoDesbalanceado);
		Nodo nodoPivote = nodoDesbalanceado.getIzq();
		Nodo auxiliarPivote = nodoPivote.getDer();

		//Hacemos el balanceo
		nodoPivote.setDer(nodoDesbalanceado);
		nodoDesbalanceado.setIzq(auxiliarPivote);

		//Cambiamos el nodo que apuntaba al desbalanceado
		if(nodoSuperior.getIzq().equals(nodoDesbalanceado)){
			nodoSuperior.setIzq(nodoPivote);
		} else
			nodoSuperior.setDer(nodoPivote);
	}

	/**
	 * Metodo que rota un subArbol con una rotacion compuesta
	 * izquierda-Derecha, dada la raiz de dicho subArbol
	 *
	 * @param nodoDesbalanceado Raiz del subArbol desbalanceado
	 */
	private void rotacionIzqDer(Nodo nodoDesbalanceado){

		Nodo nodoSuperior = this.nodoAnterior(nodoDesbalanceado);
		Nodo primerPivote = nodoDesbalanceado.getIzq();
		Nodo segundoPivote = primerPivote.getDer();

		//Hacemos el balanceo
		nodoDesbalanceado.setIzq(segundoPivote.getDer());
		primerPivote.setDer(segundoPivote.getIzq());

		segundoPivote.setIzq(primerPivote);
		segundoPivote.setDer(nodoDesbalanceado);

		//Camiamos el nodo que apuntaba al desbalanceado
		if(nodoSuperior.getIzq().equals(nodoDesbalanceado)){
			nodoSuperior.setIzq(segundoPivote);
		} else
			nodoSuperior.setDer(segundoPivote);
	}

	/**
	 * Metodo que rota un subArbol con una rotacion compuesta
	 * derecha-izquierda, dada la raiz de dicho subArbol
	 *
	 * @param nodoDesbalanceado Raiz del subArbol desbalanceado
	 */
	private void rotacionDerIzq(Nodo nodoDesbalanceado){
		Nodo nodoSuperior = this.nodoAnterior(nodoDesbalanceado);
		Nodo primerPivote = nodoDesbalanceado.getDer();
		Nodo segundoPivote = null;
		if(primerPivote.getIzq()!=null) {
			segundoPivote = primerPivote.getIzq();
		}
		//Hacemos el Balanceo
		primerPivote.setIzq(segundoPivote.getDer());
		nodoDesbalanceado.setDer(segundoPivote.getIzq());

		segundoPivote.setIzq(nodoDesbalanceado);
		segundoPivote.setDer(primerPivote);

		//Cambiamos el nodo que apuntaba al desbalanceado
		if(nodoSuperior.getIzq().equals(nodoDesbalanceado)){
			nodoSuperior.setIzq(segundoPivote);
		} else
			nodoSuperior.setDer(segundoPivote);
	}


	/**
	 * Metodo que comprueba si, dada la raiz de un subArbol, ese
	 * subArbol esta desbalanceado, y que clase de rotacion necesita
	 * @param raiz raiz del subArbol
	 */
	private void reequilibrioAVL(Nodo raiz){
		int desequilibrio = calcFactorDesequilibrio(raiz);
		int desequilibrioIzq;
		int desequilibrioDcha;

		if(raiz.getIzq()==null){
			 desequilibrioIzq=0;
		} else {
			 desequilibrioIzq = calcFactorDesequilibrio(raiz.getIzq());
		}
		if(raiz.getDer()==null){
			desequilibrioDcha = 0;
		} else {
			desequilibrioDcha = calcFactorDesequilibrio(raiz.getDer());
		}

		if(desequilibrio == 2){
			if(desequilibrioDcha == 1){
				rotacionIzquierda(raiz);
			} else if(desequilibrioDcha == -1){
				rotacionDerIzq(raiz);
			}
		} else if(desequilibrio == -2){
			if(desequilibrioIzq == -1){
				rotacionDerecha(raiz);
			} else if(desequilibrioIzq == 1){
				rotacionIzqDer(raiz);
			}
		}
	}

	private Nodo nodoAnterior(Nodo nodo){
		List <Nodo> lista = super.buscar(super.raiz, nodo.getDato());
		return lista.get(1);
	}


	private boolean esHoja (Nodo nodo){
		return nodo.getDer() == null && nodo.getIzq() == null;
	}
}

