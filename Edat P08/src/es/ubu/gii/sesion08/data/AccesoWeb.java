package es.ubu.gii.sesion08.data;

import java.time.LocalDateTime;
import java.util.Objects;

public class AccesoWeb implements Comparable<Object> {

	/**
	 * Enum que contiene los tipo de logs que se van a generar.
	 */
	public enum SeccionWeb {
		Inicio, Servicios, Organizacion, Productos, Prensa, Blog, Contacto
	}

	/**
	 * String que almacena el valor de la ip correspondiente al acceso.
	 * Formato: 4 campos decimales positivos menores que 256.
	 * Ejemplo: 192.168.0.0
	 */
	private String IP;

	/**
	 * Tiempo del acceso.
	 */
	private LocalDateTime fechaYHora;

	/**
	 * Atributo que almacena el tipo de acceso que se ha registrado.
	 */
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

	/**
	 * Getter de IP,
	 * @return string.
	 */
	public String getIP() {
		return IP;
	}
	/**
	 * Getter de IP
	 * @return string.
	 */
	public LocalDateTime getFechaYHora() {
		return fechaYHora;
	}

	/**
	 * Getter del tipo de acceso.
	 * @return valor del enum.
	 */
	public SeccionWeb getDestino() {
		return destino;
	}

	/**
	 * Función que permite modificar/asignar el valor correspondiente al tipo de acceso.
	 * @param procedencia valor del enum.
	 */
	public void setProcedencia(SeccionWeb procedencia) {
		this.destino = procedencia;
	}

	/**
	 * Método toString().
	 * @return un string con todos los campos.
	 */
	@Override
	public String toString() {
		String s = "";
		s = s + destino.toString() + " - ";
		s = s + fechaYHora.toString() + " - ";
		s = s + IP.toString();
		return s;
	}

	/**
	 * Método equals.
	 * @param o Objeto cualquiera.
	 * @return True o False si los campos de los objetos coinciden.
	 */
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

	/**
	 * Método hashCode()
	 * @return el hashCode correspondiente a los 3 objetos.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(IP, fechaYHora, destino);
	}

	/**
	 * Compara dos objetos y devuelve si es mayor o menor.
	 * @param o Objeto a comparar.
	 * @return Entero. Si es positivo es mayor. Si es negativo menor. 0 Si es igual o no es comparable.
	 */
	@Override
	public int compareTo(Object o) {

		if (this.equals(o))
			return 0;

		if (o instanceof AccesoWeb){
			AccesoWeb acceso = (AccesoWeb) o;

			int comparacionFechas = acceso.getFechaYHora().compareTo(this.getFechaYHora());

			return comparacionFechas == 0 ? acceso.getIP().compareTo(this.getIP()) : comparacionFechas;
		}

		return 0; //para que no se añada al set.
	}
}
