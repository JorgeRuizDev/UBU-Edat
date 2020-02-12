package es.ubu.inf.edat.edat1920_p01;

import java.util.Iterator;

import es.ubu.inf.edat.edat1920_p01.datos.*;

public class GestorMatriculados_M implements Iterable<Moto>{

	private Vehiculo[] listado = new Vehiculo[25]; 
	
	public int cargaListado(Vehiculo[] nuevos){
		
		int tam = 0;
		
		for (int i=0; i<nuevos.length; i++){
			listado[i] = nuevos[i];
			tam ++;
		}
		
		return tam;	
		
	}
	
	public int vaciaListado(){
		
		int tam = 0;
		
		for (int i=0; i<listado.length; i++){
			listado[i] = null;
		}
		
		return tam;	
		
	}
	
	public Vehiculo[] getMatriculados(){
		return listado;
	}
	
	public Vehiculo[] listadoMarca(String marca){
		
		Vehiculo[] listadoMarca = new Vehiculo[listado.length];
		
		for(int i=0; i<listado.length; i++){
			Vehiculo actual = listado[i];
			if(actual.getMarca().equalsIgnoreCase(marca))
				listadoMarca[i] = actual;
		}
		return listadoMarca;
	}

	public Moto[] listadoMotoMarca(String modelo){
		
		Moto[] listadoMarca = new Moto[listado.length];
		
		for(int i=0; i<listado.length; i++){
			Vehiculo actual = listado[i];
			if( actual instanceof Moto && actual.getMarca().equalsIgnoreCase(modelo))
				listadoMarca[i] = (Moto) actual;
		}
		return listadoMarca;
	}

	@Override
	public Iterator<Moto> iterator() {
		return new IteradorMotos();
	}

	/** 
	 * implementación de un iterador para la clase GestorMatriculados_M
	 * 
	 *@author Jorge Ruiz Gómez
	 *
	 */

	public class IteradorMotos implements Iterator <Moto> {

		/**
		 * Entero que almacena la posición de la última moto encontrada en la lista.
		 */
		private int ultimaPosicionMoto;
		
		/**
		 * Entero que almacena el número de motos total.
		 */
		private int numeroDeMotos;
		
		/**
		 * Entero que almacena el número de motos. Se actualiza cada vez que encuentra una moto.
		 */
		private int numeroDeMotosEncontradas;
		
		/**
		 * Constructor de la clase.
		 */
		public IteradorMotos(){
			
			ultimaPosicionMoto=listado.length;
			numeroDeMotos = this.obtenerNumeroDeMotos();
			numeroDeMotosEncontradas = 0;
			
		}
		
		/**
		 * Método que permite obtener el número de motos que hay en el objeto actual.
		 * @return
		 */
		private int obtenerNumeroDeMotos() {
			int numeroDeMotos=0;
			
			for (Vehiculo vehiculo : listado) {
				if (vehiculo instanceof Moto) {
					numeroDeMotos++;
				}
			}
			
			return numeroDeMotos;
		}
		
		@Override
		public boolean hasNext() {
			
			if (this.ultimaPosicionMoto == 0) {
				return false;
			}
			if (this.numeroDeMotos <= this.numeroDeMotosEncontradas) {
				return false;
			}
				
			return true;
			
		}

		@Override
		public Moto next() {
			if (this.hasNext()) {
				for (int i=this.ultimaPosicionMoto -1 ; i >= 0; i--) {
					if (listado[i] instanceof Moto) {
						this.numeroDeMotosEncontradas++;
						ultimaPosicionMoto=i;
						return (Moto) listado[i];
					}
				}
			}
			
			return null;
			
		}
		
		
	}
	
}