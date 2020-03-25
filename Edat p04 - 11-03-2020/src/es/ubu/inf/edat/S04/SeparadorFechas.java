package es.ubu.inf.edat.S04;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase de librería (solo contiene metodos estáticos auxillares).
 * Incluye el método de resumen de eventos que permite "empaquetar" en una lista la fecha inicial y final de dicho evento. 
 * 
 * @author bbaruque
 * @author Jorge Ruiz
 * @author Alejandro Ortega
 */
public class SeparadorFechas {

	/**
	 * Se nos facilita un log de acciones que se han ido recogiendo a lo largo de la sesión de un usuario sobre nuestro sistema.
	 * Por simplicidad, cada acción se representará en nuestro caso solo por la fecha y hora del mismo. 
	 * Se quiere ahora agrupar los eventos concretos que se pueden localizar en esa sesión. Un evento concreto se determina 
	 * porque el intervalo de tiempo entre una acción y la siguiente será corto (y se podrá determinar por el usuario).
	 * 
	 * @param listaFechas: una cola de instantes de tiempo (fecha y hora) consecutivas en el tiempo. 
	 * Por su condición de cola, estas no se pueden desordenar o extraer en orden alterno.
	 * Contiene el conjunto completo de acciones (fechas) recogido en la interacción con el sistema.
	 * @param maxSeparacion: la duración máxima entre fechas para considerarlo como parte del mismo evento. 
	 * Todas aquellas fechas que tengan un espacio entre ellas igual o menor a esta duración se consideran agrupadas en el mismo evento. 
	 * @return conjunto de los eventos encontrados (es decir listas de LocalDateTime) en una estructura que
	 * contenga listas de solo 2 fechas: la inicial y la final del evento. 
	 * Dentro de la colección de tipo Deque que se devuelve, se pide organizar del evento más reciente (en la cabeza de la cola),
	 * compuesto por el grupo de fechas más reciente hasta el más antiguo (en la cola de la cola). 
	 */
	public static Deque<List<LocalDateTime>> separaEventos (Deque<LocalDateTime> listaFechas, Duration maxSeparacion) {
		
		//cola que vamos a devolver
		Deque<List<LocalDateTime>> colaFinal = new LinkedList<List<LocalDateTime>>();
		
		//intervalo en el que vamos a guardar los eventos completos
		Deque <LocalDateTime> intervalo = new LinkedList<LocalDateTime>();
		
		//flags de control
		boolean finIntervalo = false;
		boolean listaTerminada = false;

		
		LocalDateTime primeraFecha = null;
		LocalDateTime segundaFecha = null;
		
		if(listaFechas.isEmpty()) {
			return colaFinal;
		}
		
		while (! listaTerminada) {
			
			//Sólo en la primera iteración: Cargamos los valores iniciales
			if (primeraFecha == null && listaFechas.size() > 2) {
				primeraFecha = listaFechas.poll();
				segundaFecha = listaFechas.poll();
				intervalo.add(primeraFecha);
			}
							
			//si dos valores están en el intervalo:
			if (Duration.between( primeraFecha,segundaFecha).compareTo(maxSeparacion) <= 0 ) {
				intervalo.add(segundaFecha);
				finIntervalo = false;
				
				//si se ha vaciado la lista con el add anterior:
				if(listaFechas.isEmpty())
					finIntervalo = true;	//marcamos el fin del intervalo & programa
			}
			else {
				finIntervalo = true;
			}
			
			//si la diferencia entre dos elementos es mayor que maxSeparacion -> Devolvemos el intervalo hasta el momento
			if (finIntervalo) {
				
				//si existe intervalo
				if (intervalo.size() > 1) {
					LinkedList<LocalDateTime> intervaloDevolver = new LinkedList<LocalDateTime>();					
					intervaloDevolver.add(intervalo.pollFirst());
					intervaloDevolver.add(intervalo.pollLast());
					
					colaFinal.addFirst(intervaloDevolver);
				}
				
				//Reseteamos los elementos
				intervalo = new LinkedList<LocalDateTime>();
				intervalo.add(segundaFecha);
				finIntervalo = false;
				

			}
			
			
			
			
			//Calculamos las siguientes fechas
			if (listaFechas.isEmpty()) {
				listaTerminada = true;
			}
			else {
				primeraFecha=segundaFecha;
				segundaFecha=listaFechas.poll();
			}
			
		}

		return colaFinal;
	}
}
