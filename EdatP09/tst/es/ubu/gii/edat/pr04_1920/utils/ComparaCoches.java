package es.ubu.gii.edat.pr04_1920.utils;

import java.util.Comparator;

public class ComparaCoches<T> implements Comparator<T> {

	/***************** Metodos de la Interfaz ************************/
	public int compare(T o1, T o2) throws ClassCastException {
		// Comparamos segun el No. de caballos
		return ((Coche) o1).getCilindrada() - ((Coche) o2).getCilindrada();
	}

	public boolean equals(Object o) {
		return this.equals(o);
	}

} // Fin clase