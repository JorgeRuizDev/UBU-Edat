package es.ubu.gii.edat.pr03_1920;

import java.util.*;


public class HashMapTable<R,C,V> implements Table{

	/**
	 * Mapa principal que guarda todas las filas.
	 */
	private final HashMap <R,Map<C,HashMapCell<R,C,V>>>  principal ;

	
	
	/**
	 * Clase Celda que guarda cada valor asociado a su fila y a su columna.
	 * @author Alejandro Ortega
	 * @author Jorge Ruiz
	 * @param <R> Fila
	 * @param <C> Columna
	 * @param <V> Valor
	 */
	public class HashMapCell<R,C,V> implements Table.Cell{

		/**
		 * Valor de la fila
		 */
		 private final R row;
		 
		 /**
		  * Valor de la columna
		  */
		 private final C column;
		 
		 /**
		  * Valor almacenado
		  */
		 private V value;
		 
		/**
		 * Constructor que da valor inicial al la fila y a la columna.
		 * @param row fila.
		 * @param column columna.
		 */
		 public HashMapCell(R row, C column) {
			 this.row = row;
			 this.column = column;
		 }
		
		 /**
		  * Devuelve la fila.
		  */
		@Override
		public R getRowKey() {
			return row;
		}

		/**
		 * Devuelve la columna.
		 */
		@Override
		public C getColumnKey() {
			return column;
		}

		/**
		 * Devuelve el valor.
		 */
		@Override
		public V getValue() {
			return value;
		}

		/**
		 * Almacena un valor en la celda.
		 * @param value valor a almacenar
		 */
		@Override
		public V setValue(Object value) {
			// TODO Mirar a ver que cojones pasa aqui
				this.value = (V) value;
				return (V) this.value;
		}

		@Override
		public boolean equals(Object o){

			//TODO: Implementar
			return false;
		}
	}

	/**
	 * Constructor que inicializa el mapa principal.
	 */
	public HashMapTable() {
		principal = new HashMap <> ();
	}
	
	/**
	 * Inserta un nuevo objeto de tipo HashMapCell en la tabla.
	 * Primero comprueba si la fila ya se ha creado, si es asi, inserta la columna y la celda.
	 * Si no ha sido creada la fila, la crea, e inserta la columna y el objeto celda.
	 * @param row Fila de la tabla.
	 * @param column Columna de la tabla.
	 * @param value Objeto a insertar en la tabla.
	 *
	 * @return null si la fila y columna estan vacías. El objeto que ha sido reemplazado si estaban ocupadas.
	 */
	@Override
	public Object put(Object row, Object column, Object value) {

		HashMapCell<R,C,V> celda = new HashMapCell((R) row, (C) column);

		celda.setValue(value);

		Object objetoAntiguo = null;
		//Si existe la celda:
		if(principal.containsKey((R) row)){

			Map fila = (HashMap) principal.get(row);
			objetoAntiguo = fila.put(column, celda);
		} else {//Si no existe la fila, la creamos
			Map fila = new HashMap<C,HashMapCell>();
			principal.put((R) row, fila);
			fila.put(column, celda);

		}

		return objetoAntiguo;
	}

	/**
	 * Metodo que elimina una celda.
	 * Primero comprueba si existe la fila, y luego la columna.
	 */
	@Override
	public Object remove(Object row, Object column) {

		if(principal.containsKey(row)) {
			Map columna = (HashMap) principal.get(row);
			if (columna.containsKey(column)) {
			    HashMapCell celda = (HashMapCell) columna.get(column);
				columna.remove(column);
				return celda.getValue();
			}
		}
		return null;
	}

	/**
	 * Devuelve el valor correspondiente asociado a una determinada combinación de claves de fila
	 * y de columna o null si no existe esa asociación.
	 *
	 * @param row Clave del fila de la asociación correspondiente al valor a recuperar
	 * @param column Clave de columna de la asociación correspondiente al valor a recuperar
	 * @return Valor asociado a las dos claves facilitadas o null si no existe esa asociación
	 */
	@Override
	public Object get(Object row, Object column) {
		Map filaTabla;

		if (!principal.containsKey(row))
			return null;

		filaTabla = principal.get(row);

		if (!filaTabla.containsKey(column))
			return null;

		//obtenemos la celda
		HashMapCell <R,C,V> celda = (HashMapCell <R,C,V>) filaTabla.get(column);

		return celda.getValue();
	}

	/**
	 * Devuelve verdadero si la tabla contienen una asociación que incluya las claves de fila
	 * y de columna que se especifican
	 *
	 * @param row Clave del fila de la asociación que se pretende consultar
	 * @param column Clave de columna de la asociación que se pretende consultar
	 * @return true si existe la asociacion y false en otro caso
	 */
	@Override
	public boolean containsKeys(Object row, Object column) {
		if (principal.containsKey(row)){
			return principal.get(row).containsKey(column);
		}

		return false;
	}

	@Override
	public boolean containsValue(Object value) {

		for (Map.Entry <R,Map<C,HashMapCell<R,C,V>>> fila : principal.entrySet())
		{//Iteramos sobre las filas.
			Map <C,HashMapCell<R,C,V>> celdasEnLaFila = fila.getValue();

			for (Map.Entry <C, HashMapCell <R,C,V >> celda : celdasEnLaFila.entrySet() )
			{//Iteramos sobre las columnas.
				if ( celda.getValue().getValue().equals(value))
					return true;
			}
		}

		return false;
	}

	@Override
	public Map row(Object rowKey) {
		return principal.get(rowKey);
	}

	@Override
	public Map column(Object columnKey) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Devuelve el contenido completo de la tabla en forma de una conjunto de tripletas de
	 * clave de fila / clave de columna / valor. Se tratará de una vista, por lo que los cambios sobre
	 * los datos de la colección se realizarán también sobre los datos contenidos en la tabla.
	 *
	 * Cada una de las tripletas de datos se almacenará en una clase que implemente el interfaz
	 * Table.Cell (incluido en este fichero).
	 *
	 * La colección de celdas no deberá soportar los metodos de add o addAll.
	 *
	 * @return contenido
	 */
	@Override
	public Collection<Table.Cell<R,C,V>> cellSet(){
	//FIXME: No se si funciona. Debería, pero igual no jijijiji (No hay Test, igual hago uno)
		List<Cell<R,C,V>> cellSet = new LinkedList <>();

		for (Map.Entry <R,Map<C,HashMapCell<R,C,V>>> fila : principal.entrySet())
		{//Iteramos sobre las filas.
			Map <C,HashMapCell<R,C,V>> celdasEnLaFila = fila.getValue();

			for (Map.Entry <C, HashMapCell <R,C,V >> celda : celdasEnLaFila.entrySet() )
			{//Añadimos la celda correspondiente a fila-columna
				cellSet.add(celda.getValue());
			}
		}
		//System.out.println(cellSet.size());
		return cellSet();
	}


	/**
	 * Devuelve el numero de asociaciones de clave de columna / clave de fila / valor que
	 * se encuentran almacenadas en el mapa.
	 *
	 * @return numero de sociaciones almacenadas en el mapa.
	 */
	@Override
	public int size() {
		int numCeldas = 0;
		//Obtenemos el número de columnas ocupadas por cada fila.
		for ( Map.Entry <R,Map<C,HashMapCell<R,C,V>>> fila : principal.entrySet()){
			numCeldas += fila.getValue().size();
		}
		//Devolvemos el número de columnas
		return numCeldas;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void clear() {
		principal.clear();
	}
	

}
