package es.ubu.inf.edat.P01_1920;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.IIOException;

import es.ubu.inf.edat.P01_1920.data.DataLoader;

/**
 * Clase con métodos estáticos que permiten calcular el problema "3SUM" con dos métodos distintos.
 * 	Método 1: Random: Recorre todos los arrays probando cada una de las combinaciones
 *  Método 2: Ordered: Prueba todas las cobinaciones posibles con sólo dos números, y luego busca
 *  			si la lista contiene el elemento restante para completar el problema mediante búsqueda
 *  			búsqueda binaria.
 * @author Jorge Ruiz Gómez
 * @author Alejandro Ortega Martínez
 */
public class ThreeSumCalculation {

	/**
	 * Método raíz: Permite ejecutar el benchamark con todos los enteros que se encuentren en el directorio ./data
	 * y genera un archivo .csv con los tiempos de ejecución de cada ejecución.
	 * @param args Nada. 
	 */
    public static void main(String[] args)  { 
    	args=null;
    	String directorioDatos="./data";
    	
    	PrintWriter archivoResultados = null;
    	
    	long t1 = 0,t2,t3,t4;	//tiempos
    	
    	DataLoader in = null;
    	
    	try {
	        in = new DataLoader();
	        
	        File directorio = new File (directorioDatos);
	        String [] listadoArchivos = directorio.list();
	        
	        //Archivo .csv
	        new FileWriter("resultados.csv",false).close();	//Sin append, para borrar los contenidos
	        archivoResultados = new PrintWriter (new FileWriter("resultados.csv",true));
	        
	        //cabecera del archivo:
	        archivoResultados.printf("nº Elemenetos,Método Aleatorio, Método Ordenados\n");
	        
	        for (String archivo : listadoArchivos) {
	        	
	        	archivo = directorioDatos + "/" + archivo;
		        in.LoadFile(archivo);
		        int[] a = in.readAllInts();
		        in.close();
		        
		        System.out.printf("Calculando con los %d enteros del archivo: %s\n",a.length,archivo);
		        
		        t1 = System.currentTimeMillis();
		        int[][] result = findTriplets_random(a);
		        t2 = System.currentTimeMillis();
		        
		        
		        t3 = System.currentTimeMillis();
		        result = findTriplets_ordered(a);
		        t4 = System.currentTimeMillis();
		        
		        archivoResultados.printf("%d,%d,%d\n",a.length,t2-t1,t4-t3);
	        }
        }catch(IOException e) {
	        	e.printStackTrace();
	    }finally {
	    	archivoResultados.close();
	    	System.out.printf("Fin de la ejecucion\n");
	    }
    }
	
    
    /**
     * Método 1.
     * Descripción: Encuentra mediante fuerza bruta todas las tripletas, a partir de probar todas las combinaciones.
     * @param a Array de enteros con el que calcuar 3SUM, no debe haber enteros repetidos.
     * @return un array con n tripletas (siendo una tripleta un array de 3 enteros).
     */
    public static int[][] findTriplets_random(int[] a){

    	ArrayList<int[]> devolver = new ArrayList<int[]>();
    	
    	for (int i= 0; i<a.length; i++) {
    		for(int j = i+1; j<a.length; j++) {
    			for(int k = j+1; k<a.length; k++) {
    				if (a[i]+a[j]+a[k]==0) {
    					int[] temp= new int[3];
    					temp[0] = a[i];
    					temp[1]=a[j];
    					temp[2]=a[k];
    					devolver.add(temp);
    					
    				}
    			}
    		}
    	}
    	
    	int [][] devolverArray=devolver.toArray(new int[devolver.size()][3]);
		return devolverArray;
    }
    

    /**
     * Método 2.
     * Descripción: utiliza la búsqueda binaria para encontrar el tercer número restante que forme una tripleta.
     * @see busquedaBinaria
     * @param a Array de enteros con el que calcuar 3SUM, no debe haber enteros repetidos.
     * @return un array con n tripletas (siendo una tripleta un array de 3 enteros).
     */
    public static int[][] findTriplets_ordered(int[] a){

    	int[] b = a.clone();
    	Arrays.sort(b);
    	
    	ArrayList<int[]> devolver = new ArrayList<int[]>();
    	
    	for (int i= 0; i<b.length; i++) {
    		for(int j = i+1; j<b.length; j++) {
    			int buscado = -(b[i]+b[j]);
    			//System.out.println("Combinación a buscar: "+b[i] +"+" +b[j]+ "=" + buscado);
    			int posicionBuscado = busquedaBinaria(b,buscado);
    			
    			if(  posicionBuscado>=0 && posicionBuscado > j) {
    				int[] temp = new int[3];
    				temp[0]=b[i];
    				temp[1]=b[j];
    				temp[2]=buscado;
    				devolver.add(temp);
    			}
    		}
    	}
    	
    	int [][] devolverArray=devolver.toArray(new int[devolver.size()][3]);
		return devolverArray;
    }
    
    /**
     * Realiza la busqueda binaria de un elemento sobre un array. Devolverá la posición en la que se encuentra el elemento o un número negativo si el elemento no se enceuntra en el array.
     * Si el array contiene el mismo elemento en varias posiciones, se devolverá cualquiera de ellas.
     * Se recuerda que para poder realizar la implementación de la búsqueda binaria, el array debe ser antes ordenado.
     * 
     * 
     * @param array contenedor en el que se realiza la búsqueda.
     * @param buscado elemento a localizar.
     * @return posicion en la que se encuentra el elemento o número negativo en caso de no encontrarse.
     */
    public static int busquedaBinaria(int[] array, int buscado) {

    	int puntero = array.length/2;
    	int limiteInferior = 0;
    	int limiteSuperior =array.length -1;
    	Boolean flag = true;
    	
    	if (buscado < array[limiteInferior] || buscado > array[limiteSuperior]) {
    		flag = false;
    	}
    	
    	while (flag == true) {
    		if(buscado==array[puntero]) {
        		return puntero;
        	} 
    		else if (buscado == array[limiteSuperior]) {
    			return limiteSuperior;
    		}
    		else if (buscado == array[limiteInferior]) {
    			return limiteInferior;
    		}
    		else if(buscado<array[puntero] && buscado>array[limiteInferior]) {
        		limiteSuperior=puntero;
        		puntero = (limiteSuperior + limiteInferior)/2;
        	} else if(buscado>array[puntero] && buscado<array[limiteSuperior]) {
        		limiteInferior=puntero;
        		puntero = (limiteInferior + limiteSuperior)/2;
        	}
    		
    		if(limiteSuperior-limiteInferior<=1) {
        		if (array[limiteSuperior]==buscado) {
        			return limiteSuperior;
        		}
        		if (array[limiteInferior]==buscado) {
        			return limiteSuperior;
        		}
        		flag = false;
        	}
    		
    	}
    	
    	

		return -1;

    }
}