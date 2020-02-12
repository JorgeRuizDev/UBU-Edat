package es.ubu.inf.edat.edat1920_p01.datos;


/**
 * @author bbaruque
 * @author prenedo
 * @author Jorge Ruiz Gómez
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
	
	@Override
	/**
	 * Método que compara si dos objetos (de tipo Moto) son iguales.
	 * @see Moto
	 * @param object Un objeto cualquiera a comparar.
	 */
	public boolean equals (Object object) {
		
		if (! (object instanceof Moto)) {
			return false;
		}
		
		
		Moto motoAcomparar = (Moto) object;
		
		
		if (this.getMarca() == motoAcomparar.getMarca()
				&& this .getModelo() == motoAcomparar.getModelo()) {
			return true;
		}else {
			
			return false;
		}
	}

}
