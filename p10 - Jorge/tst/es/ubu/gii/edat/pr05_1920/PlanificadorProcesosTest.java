package es.ubu.gii.edat.pr05_1920;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO Eliminar los imports para entregar a los alumnos
import es.ubu.gii.edat.datos.Proceso;
import es.ubu.gii.edat.pr05_1920.PlanificadorProcesos;

import static org.junit.Assert.*;

public class PlanificadorProcesosTest {

	PlanificadorProcesos planificador;
	
	@Before
	public void setUp() throws Exception {
		planificador = new PlanificadorProcesos();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertaEnCola() {
		
		assertEquals(1, planificador.insertaEnCola(new Proceso("A",25,Proceso.Prioridad.MEDIA)));
		assertEquals(1, planificador.insertaEnCola(new Proceso("B",5,Proceso.Prioridad.ALTA)));
		assertEquals(2, planificador.insertaEnCola(new Proceso("C",25,Proceso.Prioridad.ALTA)));
		assertEquals(1, planificador.insertaEnCola(new Proceso("D",20,Proceso.Prioridad.BAJA)));
		assertEquals(2, planificador.insertaEnCola(new Proceso("E",10,Proceso.Prioridad.MEDIA)));
		assertEquals(3, planificador.insertaEnCola(new Proceso("F",15,Proceso.Prioridad.MEDIA)));

	}

	@Test
	public void testAdelantaTiempo1() {

		testInsertaEnCola();
		
		assertFalse(planificador.adelantaTiempo(3));
		
		List<Proceso> alta = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.ALTA);
		List<Proceso> media = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.MEDIA);
		List<Proceso> baja = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.BAJA);
		
		assertEquals(2, alta.get(0).getTiempoProceso());
		assertEquals(25, alta.get(1).getTiempoProceso());
		
		assertEquals(7, media.get(0).getTiempoProceso());
		assertEquals(15, media.get(1).getTiempoProceso());
		assertEquals(25, media.get(2).getTiempoProceso());
		
		assertEquals(17, baja.get(0).getTiempoProceso());
		
	}

	@Test
	public void testAdelantaTiempo2() {

		testAdelantaTiempo1();
		
		assertTrue(planificador.adelantaTiempo(10));
		
		List<Proceso> alta = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.ALTA);
		List<Proceso> media = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.MEDIA);
		List<Proceso> baja = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.BAJA);
		
		assertEquals(17, alta.get(0).getTiempoProceso());
		
		assertEquals(12, media.get(0).getTiempoProceso());
		assertEquals(25, media.get(1).getTiempoProceso());
		
		assertEquals(7, baja.get(0).getTiempoProceso());
		
	}

	@Test
	public void testInsertaEnCola2() {

		testAdelantaTiempo2();
		
		assertEquals(1, planificador.insertaEnCola(new Proceso("G",8,Proceso.Prioridad.MUYBAJA)));
		assertEquals(3, planificador.insertaEnCola(new Proceso("H",32,Proceso.Prioridad.MEDIA)));
		assertEquals(1, planificador.insertaEnCola(new Proceso("I",12,Proceso.Prioridad.MUYALTA)));
		assertEquals(4, planificador.insertaEnCola(new Proceso("J",10,Proceso.Prioridad.MEDIA)));
		assertEquals(2, planificador.insertaEnCola(new Proceso("K",15,Proceso.Prioridad.ALTA)));
		
	}

	@Test
	public void testAdelantaTiempo3() {

		testInsertaEnCola2();
		
		assertTrue(planificador.adelantaTiempo(23));
		
		List<Proceso> muyalta = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.MUYALTA);
		List<Proceso> alta = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.ALTA);
		List<Proceso> media = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.MEDIA);
		List<Proceso> baja = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.BAJA);
		List<Proceso> muybaja = planificador.recuperaProcesosPrioridad(Proceso.Prioridad.MUYBAJA);
		
		assertEquals(0, muyalta.size());
		
		assertEquals(9, alta.get(0).getTiempoProceso());
		
		assertEquals(24, media.get(0).getTiempoProceso());
		assertEquals(32, media.get(1).getTiempoProceso());
		
		assertEquals(0, baja.size());
		assertEquals(0, muybaja.size());
		
	}
	
}
