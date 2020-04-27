package es.ubu.gii.edat.sesion08;

import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

import es.ubu.gii.sesion08.data.AccesoWeb;

/**
 * Clase que permite comprobar el funcionamiento de los diferentes tipos de Set
 * a la hora de establecer la pertenencia de un dato concreto al conjunto. Este
 * funcionamiento depende principalmente de cómo se hayan definido los datos.
 * 
 * @author bbaruque
 * @author Jorge Ruiz
 * @author Alejandro Ortega
 */
public class FiltradoAccesosUnicos {

	/**
	 * Metodo que permitirá filtrar los datos de tipo AccesoWeb obtenidos de varias
	 * fuentes (almacenado cada uno como una lista de accesos). Como resultado del
	 * método, se obtendrá un conjunto de accesos UNICOS. Se deberá emplear como
	 * estructura de apoyo una HashSet.
	 * 
	 * @param logs Lista de listas de AccesoWeb.
	 * @return HashSet con los accesos.
	 */
	public static HashSet<AccesoWeb> accesosUnicosDesordenados(Collection<? extends Collection<AccesoWeb>> logs) {

		if (logs == null)
			return null;

		HashSet <AccesoWeb> set = new HashSet<>(logs.size());

		//Volcamos cada lista de la lista de listas en el Set.
		for (Collection <AccesoWeb> log : logs)
			set.addAll(log);

		return set;
	}

	/**
	 * Método que pasado una Colección de Colecciones de AccesoWeb, generar un treeSet ordenado (según la fecha.
	 * @param logs Colección de colecciones de AccesoWeb.
	 * @return TreeSet con accesoWeb únicos y ordenados.
	 */
	public static TreeSet<AccesoWeb> accesosUnicosOrdenados(Collection<? extends Collection<AccesoWeb>> logs) {

		if (logs == null)
			return null;

		TreeSet <AccesoWeb> set = new TreeSet <> ();

		//Volcamos cada lista de la lista de listas en el Set.
		for (Collection <AccesoWeb> log : logs)
			set.addAll(log);

		System.out.println(set.size());
		return set;
	}


}
