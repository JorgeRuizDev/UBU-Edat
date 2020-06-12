package es.ubu.gii.edat.pr05_1920;

import java.util.*;

import es.ubu.gii.edat.datos.Proceso;


/**
 * Clase para la soluciónd el ejercicio planteado en la sesión 11 de prácticas de EDAT.
 * Se pretende simular un sistema sencillo de planificación de procesos
 * empleando el algoritmo de SRPT 
 * 
 * @author bbaruque
 *
 */
public class PlanificadorProcesos {

	// TODO - Se necesitará contar con una estructura de datos que permita
	// organizar los procesos a planificar (Lista de Colas de Prioridad)
	// Se contará con una cola para cada uno de los niveles de prioridad de un proceso,
	// de manera que los procesos de la misma prioridad se organizan entre sí

	private PriorityQueue <Proceso> cola_muyAlta;
	private PriorityQueue <Proceso> cola_alta ;
	private PriorityQueue <Proceso> cola_media;
	private PriorityQueue <Proceso> cola_baja;
	private PriorityQueue <Proceso> cola_muyBaja;

	/**
	 * Constructor de la clase.
	 * Es importante asegurarse de que las colas están correctamente iniciadas.
	 */
	public PlanificadorProcesos(){

		cola_muyAlta = new PriorityQueue<>();
		cola_alta = new PriorityQueue<>();
		cola_media = new PriorityQueue<>();
		cola_baja= new PriorityQueue<>();
		cola_muyBaja = new PriorityQueue<>();
		
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

		if (proc == null)
			throw new RuntimeException("Illo no existe tu prioridad, la que has liado!");


		PriorityQueue <Proceso> cola_manipulada;

		switch (proc.getPrioridad()){
			case MUYALTA:
				cola_manipulada = this.cola_muyAlta;
				break;
			case ALTA:
				cola_manipulada = this.cola_alta;
				break;
			case MEDIA:
				cola_manipulada = this.cola_media;
				break;
			case BAJA:
				cola_manipulada = this.cola_baja;
				break;
			case MUYBAJA:
				cola_manipulada = this.cola_muyBaja;
				break;
			default:
				throw new RuntimeException("Illo no existe tu prioridad, la que has liado!");
		}

		cola_manipulada.offer(proc);

		return cola_manipulada.size();
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

		boolean hay_adelantamiento = false;

		if (cola_muyAlta.size() >0){
			int t_restante = tiempo;
			Proceso proceso;

			do {
				proceso = cola_muyAlta.peek();
				t_restante = tiempo - proceso.getTiempoProceso();

				proceso.consumeTiempoProceso(tiempo);

				if (! proceso.consumeTiempoProceso(tiempo)){
					cola_muyAlta.poll();
					hay_adelantamiento = true;
				}

			}
			while (cola_muyAlta.size() > 0 && t_restante > 0);

		}




		if (cola_alta.size() >0){
			int t_restante = tiempo;
			Proceso proceso;

			do {
				proceso = cola_alta.peek();
				int tiempo_base = proceso.getTiempoProceso();
				if (! proceso.consumeTiempoProceso(t_restante)){
					cola_alta.poll();
					hay_adelantamiento = true;
				}
				t_restante = t_restante - tiempo_base;
			}
			while (cola_alta.size() > 0 && t_restante > 0);

		}

		if (cola_media.size() >0){
			int t_restante = tiempo;
			Proceso proceso;

			do {
				proceso = cola_media.peek();
				int tiempo_base = proceso.getTiempoProceso();


				if (! proceso.consumeTiempoProceso(t_restante)){
					cola_media.poll();
					hay_adelantamiento = true;
				}
				t_restante = t_restante - tiempo_base;
			}
			while (cola_media.size() > 0 && t_restante > 0);

		}

		if (cola_baja.size() >0){
			int t_restante = tiempo;
			Proceso proceso;

			do {
				proceso = cola_baja.peek();
				int tiempo_base = proceso.getTiempoProceso();


				if (! proceso.consumeTiempoProceso(t_restante)){
					cola_baja.poll();
					hay_adelantamiento = true;
				}
				t_restante = t_restante - tiempo_base;
			}
			while (cola_baja.size() > 0 && t_restante > 0);

		}

		if (cola_muyBaja.size() >0){
			int t_restante = tiempo;
			Proceso proceso;

			do {
				proceso = cola_muyBaja.peek();
				int tiempo_base = proceso.getTiempoProceso();


				if (! proceso.consumeTiempoProceso(t_restante)){
					cola_muyBaja.poll();
					hay_adelantamiento = true;
				}
				t_restante = t_restante - tiempo_base;
			}
			while (cola_muyBaja.size() > 0 && t_restante > 0);

		}

		return hay_adelantamiento;
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
				PriorityQueue<Proceso> clonMA = new PriorityQueue<>(cola_muyAlta);
				for(int i = 0 ; i<clonMA.size() ;){
					procesos.add(clonMA.poll());
				}
				break;

			case ALTA:
				PriorityQueue<Proceso> clonA = new PriorityQueue<>(cola_alta);
				for(int i = 0 ; i<clonA.size() ;){
					procesos.add(clonA.poll());
				}
				break;

			case MEDIA:
				PriorityQueue<Proceso> clonM = new PriorityQueue<>(cola_media);
				for(int i = 0 ; i<clonM.size() ;){
					procesos.add(clonM.poll());
				}
				break;

			case BAJA:
				PriorityQueue<Proceso> clonB = new PriorityQueue<>(cola_baja);
				for(int i = 0 ; i<clonB.size() ;){
					procesos.add(clonB.poll());
				}
				break;

			case MUYBAJA:
				PriorityQueue<Proceso> clonMB = new PriorityQueue<>(cola_muyBaja);
				for(int i = 0 ; i<clonMB.size() ; i++){
					procesos.add(clonMB.poll());
				}
				break;
		}
		return procesos;
	}
}


