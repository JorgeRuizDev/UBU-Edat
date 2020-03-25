package es.ubu.inf.edat.S04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;



import es.ubu.inf.edat.datos.GeneradorFechas_8;

public class SeparadorFechasTest {

	Deque<LocalDateTime> logFechas;
	List<LocalDateTime> evto1, evto2, evto3, evto4;

	@Test
	public void test4Eventos() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(5, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(10));
		evto2 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(6));
		evto3 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(20));
		evto4 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(5));

		logFechas = new LinkedList<>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		logFechas.addAll(evto4);

		System.out.println(logFechas);

		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaEventos(logFechas, Duration.ofMinutes(5));

		System.out.println(separadas);

		assertEquals(4,separadas.size());
		
		List<LocalDateTime> ev;
		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto4.get(0));
		assertEquals(ev.get(1), evto4.get(evto4.size()-1));

		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto3.get(0));
		assertEquals(ev.get(1), evto3.get(evto3.size()-1));

		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto2.get(0));
		assertEquals(ev.get(1), evto2.get(evto2.size()-1));

		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto1.get(0));
		assertEquals(ev.get(1), evto1.get(evto1.size()-1));

	}

	@Test
	public void testEventosAlPrincipio() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(6));
		evto2 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(20));
		evto3 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(5));
		evto4 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(25));

		logFechas = new LinkedList<>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		logFechas.addAll(evto4);

		System.out.println(logFechas);

		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaEventos(logFechas, Duration.ofMinutes(5));

		System.out.println(separadas);

		List<LocalDateTime> ev;
		assertEquals(3,separadas.size());

		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto3.get(0));
		assertEquals(ev.get(1), evto3.get(evto3.size()-1));
		
		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto2.get(0));
		assertEquals(ev.get(1), evto2.get(evto2.size()-1));

		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto1.get(0));
		assertEquals(ev.get(1), evto1.get(evto1.size()-1));

	}

	@Test	
	public void testEventosAlFinal() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(25));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(10));
		evto2 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(6));
		evto3 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(5));
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(20));
		evto4 = GeneradorFechas_8.getFechasIncrementales(8, Duration.ofMinutes(5));

		logFechas = new LinkedList<>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		logFechas.addAll(evto4);

		System.out.println(logFechas);

		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaEventos(logFechas, Duration.ofMinutes(5));

		System.out.println(separadas);
		
		
		assertEquals(3,separadas.size());

		List<LocalDateTime> ev;
		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto4.get(0));
		assertEquals(ev.get(1), evto4.get(evto4.size()-1));

		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto3.get(0));
		assertEquals(ev.get(1), evto3.get(evto3.size()-1));

		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto2.get(0));
		assertEquals(ev.get(1), evto2.get(evto2.size()-1));

	}


	@Test
	public void test1EventoCompleto() {

		// 5 fechas separadas por 3 minutos (en EVENTO)
		evto1 = GeneradorFechas_8.getFechasIncrementales(5, Duration.ofMinutes(3));
		// se avanza el tiempo 2 minutos
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(2));
		// 3 fechas separadas por 3 minutos (en EVENTO)
		evto2 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(3));
		// se avanza el tiempo 2 minutos
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(2));
		// 4 fechas separadas por 3 minutos (en EVENTO)
		evto3 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(3));

		logFechas = new LinkedList<>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		
		// La diferencia máxima para finalizar el evento es 5 min. Ninguna de las fechas anteriores se separa 5 o más: todas están dentro del mismo evento.
		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaEventos(logFechas, Duration.ofMinutes(5));

		assertEquals(1,separadas.size());

		List<LocalDateTime> ev;
		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto1.get(0));
		assertEquals(ev.get(1), evto3.get(evto3.size()-1));

	}

	
	@Test
	public void test1EventoMedio() {
		
		// 5 fechas separadas por 8 minutos (no hay EVENTO)
		evto1 = GeneradorFechas_8.getFechasIncrementales(5, Duration.ofMinutes(8));
		// se avanza el tiempo 25 minutos (cambia de evento)
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(25));
		// 3 fechas separadas por 3 minutos (en EVENTO)
		evto2 = GeneradorFechas_8.getFechasIncrementales(3, Duration.ofMinutes(3));
		// se avanza el tiempo 25 minutos (cambia de evento)
		GeneradorFechas_8.incrementaTiempo(Duration.ofMinutes(25));
		// 4 fechas separadas por 10 minutos (no hay EVENTO)
		evto3 = GeneradorFechas_8.getFechasIncrementales(4, Duration.ofMinutes(10));

		logFechas = new LinkedList<>();
		logFechas.addAll(evto1);
		logFechas.addAll(evto2);
		logFechas.addAll(evto3);
		
		// La diferencia máxima para finalizar el evento es 5 min. 
		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaEventos(logFechas, Duration.ofMinutes(5));

		assertEquals(1,separadas.size());

		List<LocalDateTime> ev;
		ev = separadas.removeFirst();
		assertEquals(2,ev.size());
		assertEquals(ev.get(0), evto2.get(0));
		assertEquals(ev.get(1), evto2.get(evto2.size()-1)); 
				
	}

	@Test
	public void testVacio() {

		logFechas = new LinkedList<>();
		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaEventos(logFechas, Duration.ofMinutes(5));		
		assertTrue(separadas.isEmpty());

	}
	
	@Test
	public void testVacio2() {

		evto1 = GeneradorFechas_8.getFechasIncrementales(5, Duration.ofMinutes(10));
		
		logFechas = new LinkedList<>();
		logFechas.addAll(evto1);
		
		Deque<List<LocalDateTime>> separadas = SeparadorFechas.separaEventos(logFechas, Duration.ofMinutes(5));		
		assertTrue(separadas.isEmpty());

	}

}
