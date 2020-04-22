package es.ubu.gii.sesion08.data;

import java.time.LocalDateTime;
import java.util.Objects;

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
		return Objects.hash(IP, fechaYHora, destino);
	}


	@Override
	public int compareTo(Object o) {

		if (this.equals(o))
			return 0;

		if (o instanceof AccesoWeb){
			AccesoWeb acceso = (AccesoWeb) o;

			//Comparamos las horas

			return acceso.getFechaYHora().compareTo(this.getFechaYHora());
		}

		return 0; //para que no se añada a el set.
	}
}
