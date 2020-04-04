package es.ubu.gii.edat.sesion06;

import java.util.*;

/**
 * Clase que permite implementar un diccionario bilingüe para obtener 
 * la traducción de palabras entre dos idiomas empleando  alguna de las 
 * clases que implementan HashMap.
 * 
 * En este caso se implementan funciones más avanzadas sobre la original.
 * 
 * @author bbaruque
 * @author Jorge Ruiz
 * @author Alejandro Ortega
 */

public class TraductorAvanzado {

	/**
	 * Se deberá instanciar un mapa que contendrá las traducciones para realizar luego la consulta.
	 * En este caso el mapa deberá implementarse como un multimapa para las operaciones avanzadas.
	 */
	private Map <String, List <String>> diccionario = new HashMap<>();

	/**
	 * Método que permite almacenar las diferentes traducciones dentro del Mapa
	 * creado para ello. Se pasan dos arrays de cadenas (del mismo tamaño), cada
	 * uno contiene en la misma posición la traducción de una palabra en su correspondiente
	 * idioma. Se considera el idioma que se pasa como primer parámetro el idioma
	 * que se va a emplear para consultar (esto es el idioma nativo del usuario)
	 * y el segundo el que se obtendrá como respuesta.  
	 * 
	 * Cada palabra tiene en este caso una o varias traducciones. Esto implica que la misma
	 * palabra puede aparece más de una vez dentro del mismo vector, teniendo como traduccion
	 * palabras diferentes en el otro vector. Se deberá almacenar por lo tanto una palabra
	 * junto con todas las posibles traducciones de la misma para su posterior consulta.
	 * 
	 * @param idioma1 Array de cadenas con las palabras en el idioma de consulta
	 * @param idioma2 Array de cadenas correspondientes en el idioma de respuesta.
	 * @return numero de traducciones disponibles
	 */
	public int cargaDiccionario (String[] idioma1, String[] idioma2){

		if ( idioma1.length != idioma2.length)
			return 0;
		else if (Arrays.equals(idioma1, idioma2))
			return 0;

		for (int i = 0; i< idioma1.length; i++) {

			//Si no se encuentra almacenada en el mapa:
			List<String> listaSinonimosTraducidos;
			if (!diccionario.containsKey(idioma1[i])){
				//Creamos una nueva lista y la añadimos al mapa con la lista como valor
				listaSinonimosTraducidos = new LinkedList<>();
			} else {//si existe la lista, la guardamos para modificarla más adelante
				listaSinonimosTraducidos = diccionario.get(idioma1[i]);
			}
			//añadimos la nueva palabra a la lista y la guardamos junto a su clave.
			listaSinonimosTraducidos.add(idioma2[i]);
			diccionario.put(idioma1[i], listaSinonimosTraducidos);
		}


		return this.diccionario.size();
	}

	/**
	 * Método que permite obtener la traducción en el idioma de respuesta 
	 * de una palabra facilitada en el idioma de consulta (o idioma nativo).
	 * 
	 * @param buscada palabra a traducir, escrita en el idioma de consulta
	 * @return traduccion de la palabra en el idioma de respuesta
	 * Se debe tener en cuenta que en este caso una palabra puede tener
	 * más de una traducción (se devuelve una lista)
	 */
	public List<String> buscaTraduccion(String buscada){

		if (diccionario.containsKey(buscada))
			return this.diccionario.get(buscada);
		else
			return buscaTraduccionInversa(buscada);
	}

	/**
	 * Método privado que permite buscar un elemento de forma inversa.
	 * Devuelve una lista con todos los elementos en los que está contenido el string / el string forma parte de la clave.
	 * @param buscada string a buscar.
	 * @return lista con los resultados.
	 */
	private List<String> buscaTraduccionInversa (String buscada){
		List <String> listaDevolver = new LinkedList<>();

		for (Map.Entry<String, List<String>> entradaIterada : this.diccionario.entrySet()){
			for (int i = 0; i < entradaIterada.getValue().size(); i++){
				if (entradaIterada.getValue().get(i).equals(buscada)) {

					String valorDevolver = entradaIterada.getKey();
					if (!listaDevolver.contains(valorDevolver))
						listaDevolver.add(valorDevolver);
				}
			}
		}

		return listaDevolver;
	}
	/**
	 * Método que permite obtener los sinónimos a una palabra dada.
	 * En este caso, la palabra se consultará en el idioma de respuesta.
	 * Se devolverán todas aquellas otras acepciones que se han encontrado
	 * almacenadas junto con la palabra de consulta como traducciones a 
	 * otras palabras en el idioma de consulta original (nativo) 
	 * 
	 * @param buscada palabra de la que se quieren obtener sinónimos (idioma de respuesta)
	 * @return sinónimos a la palabra introducida (puede haber más de uno)
	 */
	public List<String> buscaSinonimos(String buscada){

		//Buscamos todas las claves que contengan nuestra palabra
		List <String> resultadosBusqeudaInversa = this.buscaTraduccionInversa(buscada);

		List <String> listaSinonimos = new ArrayList<>();

		//Añadimos por cada clave todos sus valores
		for (String sinonimo : resultadosBusqeudaInversa){
			listaSinonimos.addAll(this.diccionario.get(sinonimo));
		}

		//Eliminamos la palabra buscada ya que no forma parte de los sinónimos.
		while ( listaSinonimos.remove(buscada) );

		return listaSinonimos;
	}
	
	/**
	 * Método que permite eliminar por completo todas las traducciones almacenadas.
	 */
	public void clear (){
		diccionario.clear();
	}

}
