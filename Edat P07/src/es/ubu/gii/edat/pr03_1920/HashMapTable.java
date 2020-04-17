package es.ubu.gii.edat.pr03_1920;

import java.util.*;

/**
 * Clase Tabla que guarda en un objeto Celda un valor.
 * Este valor está indexado por una fila y una columna.
 * @see HashMapCell
 * @author Alejandro Ortega
 * @author Jorge Ruiz
 * @param <R> Fila.
 * @param <C> Columna.
 * @param <V> Valor.
 *
 */

public class HashMapTable<R,C,V> implements Table <R,C,V> {

	/**
	 * Mapa principal que guarda todas las filas.
	 */
	private final HashMap <R,Map<C,HashMapCell>>  principal ;

	/**
	 * Clase Celda que guarda cada valor asociado a su fila y a su columna.
	 * @author Alejandro Ortega
	 * @author Jorge Ruiz

	 */
	public class HashMapCell implements Table.Cell <R, C, V> {

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
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		/**
		 * Método equals.
		 * @param o Objeto a comparar
		 * @return True o false.
		 */
		@Override
		public boolean equals(Object o){
			
			if (o instanceof Table.Cell) {
				//Se ha comprobado en el if del bloque que sea seguro el cast.
				@SuppressWarnings("unchecked") HashMapCell hashMapCell = (HashMapCell) o;
				return hashMapCell.getColumnKey() == this.column
						&& hashMapCell.getRowKey() == this.row
						&& hashMapCell.getValue() == this.value;
					
			}
			
			return false;
		}
		
		/**
		 * Metodo HashCode
		 * Utilizando la constante 1, aplicamos los hashcode de los atributos y los combinamos
		 */
		
		@Override
		public int hashCode() {
			 //Inicializamos la constante
			int valor = 1;
			 valor = 31 * valor + row.hashCode();
			 valor = 31 * valor + column.hashCode();
			 
			 return valor;
		}
		
	}

	/**
	 * Constructor que inicializa el mapa principal.
	 */
	public HashMapTable() {
		principal = new HashMap <>();
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
	public V put(R row, C column, V value) {

		HashMapCell celda = new HashMapCell (row, column);
		Map <C,HashMapCell> fila;
		celda.setValue(value);

		HashMapCell celdaAntigua = null;
		//Si existe la celda:
		if(principal.containsKey(row)){
			fila = principal.get(row);
			celdaAntigua =  fila.put(column, celda);
		} else {//Si no existe la fila, la creamos
			fila = new HashMap<>();
			principal.put(row, fila);
			fila.put(column, celda);
		}

		if (celdaAntigua != null)
			return celdaAntigua.getValue();
		else
			return null;
	}

	/**
	 * Elimina la asociación entre las dos claves y su valor, si existía anteriormente.
	 * Se eliminarán los 3 elementos de la tabla, no solo el valor.
	 *
	 * @param row Clave del fila de la asociación a ser eliminada
	 * @param column Clave del columna de la asociación a ser eliminada
	 * @return Valor previamente asociado a ambas claves o nulo si no existía esa asociación
	 */
	public V remove(R row, C column) {

		if(principal.containsKey(row)) {
			Map <C, HashMapCell> columna = principal.get(row);
			if (columna.containsKey(column)) {
			    HashMapCell celda = columna.get(column);
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
	public V get(Object row, Object column) {
		Map <C,HashMapCell> filaTabla;

		if (!principal.containsKey( row))
			return null;

		filaTabla = principal.get(row);

		if (!filaTabla.containsKey(column))
			return null;

		//obtenemos la celda
		HashMapCell celda = filaTabla.get(column);

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

		for (Map.Entry <R,Map<C,HashMapCell>> fila : principal.entrySet())
		{//Iteramos sobre las filas.
			Map <C,HashMapCell> celdasEnLaFila = fila.getValue();

			for (Map.Entry <C, HashMapCell> celda : celdasEnLaFila.entrySet() )
			{//Iteramos sobre las columnas.
				if ( celda.getValue().getValue().equals(value))
					return true;
			}
		}

		return false;
	}

	/**
	 * Método que devuelve una fila a partir de su clave correspondiente.
	 * @param rowKey clave de fila que se debe recuperar de la tabla
	 * @return fila.
	 */
	@Override
	public Map <C,V> row(Object rowKey) {
		Map<C,V> filas = new HashMap<>();
		Map<C,HashMapCell> filaObtenida;
		filaObtenida = principal.get(rowKey);
		
		for (Map.Entry <C, HashMapCell> celda : filaObtenida.entrySet() ) {
			filas.put(celda.getKey(), celda.getValue().getValue());
		}
	return filas;
	}


	/**
	 * Método que devuelve una columna a partir de su índice.
	 * @param columnKey clave de columna que se debe recuperar de la tabla
	 * @return columna.
	 */
	@Override
	public Map<R,V> column(Object columnKey) {
		
		Map<R,V> columna = new HashMap<>();
		
		for (Map.Entry <R,Map<C,HashMapCell>> fila : principal.entrySet())
		{//Iteramos sobre las filas.
			Map <C,HashMapCell> celdasEnLaFila = fila.getValue();

			for (Map.Entry <C, HashMapCell> celda : celdasEnLaFila.entrySet() )
			{//Iteramos sobre las columnas.
				if ( celda.getValue().getColumnKey()==columnKey) {
					columna.put(celda.getValue().getRowKey(), celda.getValue().getValue());
				}
			}
		}
		
		return columna;
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
		Set<Table.Cell<R,C,V>> cellSet = new HashSet<>();

		for (Map.Entry <R,Map<C,HashMapCell>> fila : principal.entrySet())
		{//Iteramos sobre las filas.
			Map <C,HashMapCell> celdasEnLaFila = fila.getValue();
			for (Map.Entry <C, HashMapCell> celda : celdasEnLaFila.entrySet() )
			{//Añadimos la celda correspondiente a fila-columna
				HashMapCell c = celda.getValue();
				cellSet.add(c);
			}
		}

		return Collections.unmodifiableSet(cellSet);
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
		for ( Map.Entry <R,Map<C,HashMapCell>> fila : principal.entrySet()){
			numCeldas += fila.getValue().size();
		}
		//Devolvemos el número de columnas
		return numCeldas;
	}

	/**
	 * Comprueba si la tabla tiene elementos.
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Vacía el mapa de tablas. 
	 */
	@Override
	public void clear() {
		principal.clear();
	}
	

}
