package es.ubu.inf.edat.edat1920_p01.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Moto extends Vehiculo {    

	protected String Matricula;
    private int Ruedas;
    
    /**
     * Constructor de la clase coche
     */
    public Moto(String marca,String modelo,int cilindrada){
        this.Marca = marca;
        this.Modelo = modelo;        
        this.Cilindrada = cilindrada;
    }

	public String getMatricula() {
		return this.Matricula;
	}

	public String setMatricula(String matricula) {
		return this.Matricula = matricula;
	}

	public int getRuedas() {
		return Ruedas;
	}

	public void setRuedas(int ruedas) {
		Ruedas = ruedas;
	}

}
