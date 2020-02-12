package es.ubu.inf.edat.edat1920_p01.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Camion extends Vehiculo {    

	protected String Matricula;
    private int Ejes;
    
    /**
     * Constructor de la clase coche
     */
    public Camion(String marca,String modelo,int cilindrada){
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

	public int getEjes() {
		return this.Ejes;
	}

	public void setEjes(int ejes) {
		this.Ejes = ejes;
	}
        
}
