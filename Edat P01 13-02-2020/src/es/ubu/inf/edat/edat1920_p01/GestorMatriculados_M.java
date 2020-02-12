package es.ubu.inf.edat.edat1920_p01;

import es.ubu.inf.edat.edat1920_p01.datos.*;

public class GestorMatriculados_M{

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

	/* Hacer que la clase tenga un iterador que recorra los motos de 
	 * forma secuencial DESCENDENTE 
	 */

	
}
