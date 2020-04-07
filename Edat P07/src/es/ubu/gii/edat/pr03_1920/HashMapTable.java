package es.ubu.gii.edat.pr03_1920;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapTable<R,C,V> implements Table{

	/**
	 * Mapa principal que guarda todas las filas.
	 */
	private HashMap <R,Map<C,HashMapCell<R,C,V>>>  principal ;

	
	
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
		 private R row;
		 
		 /**
		  * Valor de la columna
		  */
		 private C column;
		 
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
	 * @Param row Fila de la tabla.
	 * @Param column Columna de la tabla.
	 * @Param value Objeto a insertar en la tabla.
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

	@Override
	public Object get(Object row, Object column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKeys(Object row, Object column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map row(Object rowKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map column(Object columnKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection cellSet() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	

}
