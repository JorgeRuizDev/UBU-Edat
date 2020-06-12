package Examen;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class CadenasLongitud implements Iterable <String> {

    Set<String> almacenamiento;

    CadenasLongitud (){

        class comparador implements Comparator<String> {

            @Override
            public int compare(String o1, String o2) {
                if (o1.equals(o2))
                    return 0;
                if (o1.length() == o2.length())
                    return 1;
                return o1.length() - o2.length();
            }
        }

        almacenamiento = new TreeSet<>(new comparador());
    }

    boolean anadeCadenas (String [] cadenas){

        boolean return_value = false;

        for (String cadena : cadenas){
            if (almacenamiento.add(cadena))
                return_value = true;
        }
        return return_value;
    }


    public Iterator <String> iterator (){
        return almacenamiento.iterator();
    }

    public static void main (String [] args){

        String [] cadenas = {"a", "aa", "aaa", "b", "bbbbbbb", "cc", "288"};

        CadenasLongitud clase= new CadenasLongitud();

        clase.anadeCadenas(cadenas);

        for (String cadena : clase){
            System.out.println(cadena);
        }

    }

}
