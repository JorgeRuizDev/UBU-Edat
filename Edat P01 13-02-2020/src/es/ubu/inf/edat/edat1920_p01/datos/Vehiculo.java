package es.ubu.inf.edat.edat1920_p01.datos;

public class Vehiculo{
	
	protected String Marca;
	protected String Modelo;    
	protected int Cilindrada;
	protected int PotenciaFiscal;
    
	protected Vehiculo(){ };
	
    public String toString() {
        return Marca + "-" + Modelo + "-" + Cilindrada + "-PF:(" + PotenciaFiscal+") \t";
    }
    /**
     * @return devuelve la cilindrada del vehiculo.
     */
    public int getCilindrada() {
        return Cilindrada;
    }
    /**
     * @param cilindrada a almacenar.
     */
    public void setCilindrada(int cilindrada) {
        Cilindrada = cilindrada;
    }
    /**
     * @return devuelve la marca del vehiculo.
     */
    public String getMarca() {
        return Marca;
    }
    /**
     * @param marca que se desea almacenar.
     */
    public void setMarca(String marca) {
        Marca = marca;
    }
    
    /**
     * @return devuelve el modelo de vehiculo.
     */
    public String getModelo() {
        return Modelo;
    }
    
    /**
     * @param modelo que se desea establecer en el vehiculo.
     */
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
 
    /**
     * @return devuelve la potencia fiscal del vehiculo.
     */
    public int getPotenciaFiscal() {
        return PotenciaFiscal;
    }
    
    /**
     * @param potencia fiscal del vehiculo.
     */
    public void setPotenciaFiscal(int potenciaFiscal) {
        PotenciaFiscal = potenciaFiscal;
    }

}
