package es.ubu.inf.edat.edat1920_p01.datos;


/**
 * @author bbaruque
 * @author prenedo
 *
 * Clase para pruebas de codigo de Estructuras de Datos
 */
public class Coche extends Vehiculo {    

	protected String Matricula;
    private int Puertas;
    
    /**
     * Constructor de la clase coche
     */
    public Coche(String marca,String modelo,int cilindrada){
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

	public int getPuertas() {
		return Puertas;
	}

	public void setPuertas(int puertas) {
		Puertas = puertas;
	}
        
}
