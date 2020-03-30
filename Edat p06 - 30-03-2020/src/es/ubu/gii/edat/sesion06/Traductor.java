package es.ubu.gii.edat.sesion06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que permite implementar un diccionario bilingüe para obtener 
 * la traducción de palabras entre dos idiomas empleando  alguna de las 
 * clases que implementan HashMap.
 * @author Jorge Ruiz Gómez
 * @author bbaruque
 *
 */
public class Traductor {

	/**
	 * Mapa que contendrá las traducciones para realizar luego la consulta.
	 */
	Map <String,String> mapa;
	/**
	 * Método que permite almacenar las diferentes traducciones dentro del Mapa
	 * creado para ello. Se pasan dos arrays de cadenas (del mismo tamaño), cada
	 * uno contiene en la misma posición la traducción de una palabra en su correspondiente
	 * idioma. Se considera el idioma que se pasa como primer parámetro el idioma
	 * que se va a emplear para consultar (esto es el idioma nativo del usuario)
	 * y el segundo el que se obtendrá como respuesta.  
	 * 
	 * Cada palabra tiene en este caso una ÚNICA traducción.
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

		mapa = new HashMap<>(idioma1.length); //instanciamos el mapa con un tamaño fijo.

		for (int i = 0; i< idioma1.length; i++){
			mapa.put(idioma1[i], idioma2[i]);
		}



		return this.mapa.size();
	}

	/**
	 * Método que permite obtener indistintamente la traducción en el idioma
	 * de respuesta de una palabra facilitada en el idioma de consulta (o idioma 
	 * nativo) o de la traduccion de una palabra del idioma de respuesta al 
	 * idioma de consulta.
	 * 
	 * OJO: Piensa bien cómo hacer la consulta de la forma más eficiente
	 * ¿Que traducción será mejor intentar en primer lugar?
	 * 
	 * @param buscada palabra a traducir, escrita en el idioma de consulta
	 * @return traduccion de la palabra en el idioma de respuesta
	 */
	public String buscaTraduccion(String buscada){
		String traduccion;

		traduccion=this.mapa.get(buscada);

		if ( traduccion == null )
			return buscaTraduccionInversa(buscada);
		else
			return traduccion;
	}

	/**
	 * Método privado que realizar la búsqueda inversa de un valor.
	 * @param buscada palabra a buscar
	 * @return el resultado o null si esta no se encuentra registrada en el traductor.
	 */
	private String buscaTraduccionInversa(String buscada){
		if (!this.mapa.containsValue(buscada))
			return null;

		for ( Map.Entry <String,String> entrada : this.mapa.entrySet() ){
				if(buscada.equals(entrada.getValue())) //Si es el mismo valor, lo devolvemos
					return entrada.getKey();
		}
		return null;
	}

	/**
	 * Método que permite eliminar por completo todas las traducciones almacenadas.
	 */
	public void clear (){
		this.mapa.clear();
	}

}
