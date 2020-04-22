package es.ubu.gii.sesion08.data;

import java.time.LocalDateTime;

public class AccesoWeb implements Comparable<Object> {

	public enum SeccionWeb {
		Inicio, Servicios, Organizacion, Productos, Prensa, Blog, Contacto
	};

	private String IP;
	private LocalDateTime fechaYHora;
	private SeccionWeb destino;

	/**
	 * Constructor de la clase.
	 * @param seccion Parámetro correspondiente a un ENUM de la sección en la que se encuentra el usuario en la web.
	 * @param time Tiempo del eveneto.
	 * @param IP Dirección IP del usuario que realizó el evento.
	 */
	public AccesoWeb(SeccionWeb seccion, LocalDateTime time, String IP) {
		this.destino = seccion;
		this.fechaYHora = time;
		this.IP = IP;
	}

	public String getIP() {
		return IP;
	}

	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	public SeccionWeb getDestino() {
		return destino;
	}

	public void setProcedencia(SeccionWeb procedencia) {
		this.destino = procedencia;
	}

	@Override
	public String toString() {
		String s = "";
		s = s + destino.toString() + " - ";
		s = s + fechaYHora.toString() + " - ";
		s = s + IP.toString();
		return s;
	}

	//////////////

	@Override
	public boolean equals(Object o) {

		if (o == this){
			return true;
		}
		else if (o instanceof AccesoWeb){

			AccesoWeb objeto = (AccesoWeb) o;

			return this.destino.equals(objeto.getDestino())
					&& this.fechaYHora.equals(objeto.getFechaYHora())
					&& this.IP.equals(objeto.getIP());
		}

		//No es la misma referencia o tipo de objeto.
		return false;
	}

	@Override
	public int hashCode() {
		// TODO A completar por el estudiante
		// TODO To be completed by the students
	}

	@Override
	public int compareTo(Object o) {
		// TODO A completar por el estudiante
		// TODO To be completed by the students
	}
}
