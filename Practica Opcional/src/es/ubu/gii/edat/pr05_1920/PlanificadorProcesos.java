package es.ubu.gii.edat.pr05_1920;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import es.ubu.gii.edat.datos.Proceso;

/**
 * Clase para la soluciónd el ejercicio planteado en la sesión 11 de prácticas de EDAT.
 * Se pretende simular un sistema sencillo de planificación de procesos
 * empleando el algoritmo de SRPT 
 * 
 * @author Alejandro Ortega Martinez Y Jorge Ruiz
 *
 */
public class PlanificadorProcesos {


	// organizar los procesos a planificar (Lista de Colas de Prioridad)
	// Se contará con una cola para cada uno de los niveles de prioridad de un proceso,
	// de manera que los procesos de la misma prioridad se organizan entre sí

	PriorityQueue<Proceso> colaMuyAlta;
	PriorityQueue<Proceso> colaAlta;
	PriorityQueue<Proceso> colaMedia;
	PriorityQueue<Proceso> colaBaja;
	PriorityQueue<Proceso> colaMuyBaja;

	
	/**
	 * Constructor de la clase.
	 * Es importante asegurarse de que las colas están correctamente iniciadas.
	 */
	public PlanificadorProcesos(){
		colaMuyAlta= new PriorityQueue<>();
		colaAlta = new PriorityQueue<>();
		colaMedia = new PriorityQueue<>();
		colaBaja = new PriorityQueue<>();
		colaMuyBaja = new PriorityQueue<>();

		
	}

	/**
	 * Metodo que permite insertar nuevos procesos en el planificador.
	 * Se debe asegurar que los procesos se distribuyen en cada cola en 
	 * funcion de su prioridad.
	 * 
	 * @param proc Proceso a introducir en el planificador
	 * @return numero de procesos incluidos en la misma cola en que se ha insertado el proceso actual 
	 * (el actual incluido) 
	 */
	public int insertaEnCola(Proceso proc){

		switch (proc.getPrioridad()) {
			case MUYALTA:
				colaMuyAlta.add(proc);
				return colaMuyAlta.size();
			case ALTA:
				colaAlta.add(proc);
				return colaAlta.size();
			case MEDIA:
				colaMedia.add(proc);
				return colaMedia.size();
			case BAJA:
				colaBaja.add(proc);
				return colaBaja.size();
			case MUYBAJA:
				colaMuyBaja.add(proc);
				return colaMuyBaja.size();
		}
		return 0;
		
	}

	/**
	 * Metodo que permite simular el paso del tiempo en el sistema completo.
	 * Se facilita un numero de instantes de tiempo a avanzar y se reproducirá 
	 * ese avance en cada una de las colas. Se considerará que cada una tiene
	 * asignada una unidad de procesamiento, por lo que el tiempo consumido en una
	 * cola no influye en el resto.
	 * 
	 * Se deberán eliminar aquellos procesos que hayan terminado su trabajo (en
	 * orden de menor a mayor tiempo) y restar el tiempo correspondiente a aquellos
	 * que no vayan a terminar en el periodo asignado. 
	 * 
	 * @param tiempo numero de instantes de tiempo que transcurren en la simulación
	 * @return true en caso de que algún proceso finalice en cualquiera de las colas y false en caso contrario
	 */
	public boolean adelantaTiempo(int tiempo){

		Boolean salidaProceso = false;

		//Comprobamos la cola MuyAlta
		if(colaMuyAlta.peek()!=null) {
			int tiempoTemp = tiempo;
			do {
				if(colaMuyAlta.peek()==null){
					break;
				}
				int tiempoProceso = colaMuyAlta.peek().getTiempoProceso();
				if (tiempoTemp < tiempoProceso) {
					colaMuyAlta.peek().consumeTiempoProceso(tiempoTemp);
					tiempoTemp = 0;
				} else if (tiempoTemp == tiempoProceso) {
					colaMuyAlta.peek().consumeTiempoProceso(tiempoTemp);
					colaMuyAlta.poll();
					salidaProceso = true;
					tiempoTemp = 0;
				} else if (tiempoTemp > tiempoProceso) {
					colaMuyAlta.peek().consumeTiempoProceso(tiempoTemp - tiempoProceso);
					colaMuyAlta.poll();
					tiempoTemp = tiempoTemp - tiempoProceso;
				}
			}while(tiempoTemp!=0);
		}
		//Comprobamos la cola Alta
		if(colaAlta.peek()!=null) {
			int tiempoTemp = tiempo;
			do {
				if(colaAlta.peek()==null){
					break;
				}
				int tiempoProceso = colaAlta.peek().getTiempoProceso();
				if (tiempoTemp < tiempoProceso) {
					colaAlta.peek().consumeTiempoProceso(tiempoTemp);
					tiempoTemp = 0;
				} else if (tiempoTemp == tiempoProceso) {
					colaAlta.peek().consumeTiempoProceso(tiempoTemp);
					colaAlta.poll();
					salidaProceso = true;
					tiempoTemp = 0;
				} else if (tiempoTemp > tiempoProceso) {
					colaAlta.peek().consumeTiempoProceso(tiempoTemp - tiempoProceso);
					colaAlta.poll();
					salidaProceso = true;
					tiempoTemp = tiempoTemp - tiempoProceso;
				}
			}while(tiempoTemp!=0);
		}

		//Comprobamos la cola Media
		if(colaMedia.peek()!=null) {
			int tiempoTemp = tiempo;
			do {
				if(colaMedia.peek()==null){
					break;
				}
				int tiempoProceso = colaMedia.peek().getTiempoProceso();
				if (tiempoTemp < tiempoProceso) {
					colaMedia.peek().consumeTiempoProceso(tiempoTemp);
					tiempoTemp = 0;
				} else if (tiempoTemp == tiempoProceso) {
					colaMedia.peek().consumeTiempoProceso(tiempoTemp);
					colaMedia.poll();
					salidaProceso = true;
					tiempoTemp = 0;
				} else if (tiempoTemp > tiempoProceso) {
					colaMedia.peek().consumeTiempoProceso(tiempoTemp - tiempoProceso);
					colaMedia.poll();
					salidaProceso = true;
					tiempoTemp = tiempoTemp - tiempoProceso;
				}
			}while(tiempoTemp!=0);

		}
		//Comprobamos la cola Baja
		if(colaBaja.peek()!=null) {
			int tiempoTemp = tiempo;
			do {
				if(colaBaja.peek()==null){
					break;
				}
				int tiempoProceso = colaBaja.peek().getTiempoProceso();
				if (tiempoTemp < tiempoProceso) {
					colaBaja.peek().consumeTiempoProceso(tiempoTemp);
					tiempoTemp = 0;
				} else if (tiempoTemp == tiempoProceso) {
					colaBaja.peek().consumeTiempoProceso(tiempoTemp);
					colaBaja.poll();
					salidaProceso = true;
					tiempoTemp = 0;
				} else if (tiempoTemp > tiempoProceso) {
					colaBaja.peek().consumeTiempoProceso(tiempoTemp - tiempoProceso);
					colaBaja.poll();
					salidaProceso = true;
					tiempoTemp = tiempoTemp - tiempoProceso;
				}
			}while(tiempoTemp!=0);
		}
		//Comprobamos la cola MuyBaja
		if(colaMuyBaja.peek()!=null) {
			int tiempoTemp = tiempo;
			do {
				if(colaMuyBaja.peek()==null){
					break;
				}
				int tiempoProceso = colaMuyBaja.peek().getTiempoProceso();
				if (tiempoTemp < tiempoProceso) {
					colaMuyBaja.peek().consumeTiempoProceso(tiempoTemp);
					tiempoTemp = 0;
				} else if (tiempoTemp == tiempoProceso) {
					colaMuyBaja.peek().consumeTiempoProceso(tiempoTemp);
					colaMuyBaja.poll();
					salidaProceso = true;
					tiempoTemp = 0;
				} else if (tiempoTemp > tiempoProceso) {
					colaMuyBaja.peek().consumeTiempoProceso(tiempoTemp - tiempoProceso);
					colaMuyBaja.poll();
					salidaProceso = true;
					tiempoTemp = tiempoTemp - tiempoProceso;
				}
			}while(tiempoTemp!=0);
		}

		return salidaProceso;
	}

	/**
	 * Metodo que permite consultar el contenido de cada una de las colas del sistema.
	 * 
	 * Se deberá devolver el contenido en el mismo orden en el que se haya planificado
	 * procesar los elementos en el momento de la llamada.
	 * 
	 * @param prioridad correspondiente a la cola que se quiere acceder
	 * @return lista con los procesos incluidos en esa cola en el orden en que se van a procesar
	 */
	public List<Proceso> recuperaProcesosPrioridad (Proceso.Prioridad prioridad){

		List<Proceso> procesos = new LinkedList<>();
		switch (prioridad) {
			case MUYALTA:
				PriorityQueue<Proceso> clonMA = new PriorityQueue<>(colaMuyAlta);
				for(int i = 0 ; i<clonMA.size() ;){
					procesos.add(clonMA.poll());
				}
				break;

			case ALTA:
				PriorityQueue<Proceso> clonA = new PriorityQueue<>(colaAlta);
				for(int i = 0 ; i<clonA.size() ;){
					procesos.add(clonA.poll());
				}
				break;

			case MEDIA:
				PriorityQueue<Proceso> clonM = new PriorityQueue<>(colaMedia);
				for(int i = 0 ; i<clonM.size() ;){
					procesos.add(clonM.poll());
				}
				break;

			case BAJA:
				PriorityQueue<Proceso> clonB = new PriorityQueue<>(colaBaja);
				for(int i = 0 ; i<clonB.size() ;){
					procesos.add(clonB.poll());
				}
				break;

			case MUYBAJA:
				PriorityQueue<Proceso> clonMB = new PriorityQueue<>(colaMuyBaja);
				for(int i = 0 ; i<clonMB.size() ; i++){
					procesos.add(clonMB.poll());
				}
				break;
		}
		return procesos;
	}

}
