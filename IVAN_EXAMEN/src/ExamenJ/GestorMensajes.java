package ExamenJ;

import java.util.*;

public class GestorMensajes {

    private Map<String, List<String>> almacenamiento ;

    public GestorMensajes(){
        almacenamiento = new HashMap<>();
    }

    int almacenaMensajes(String [] mensajes){

        for (String mensaje : mensajes){
            //Obtenemos todas las palabras del mensaje
            String [] palabras = mensaje.split(" ");

            for (String palabra : palabras){
                if (palabra.startsWith("#")){

                    List <String> mensajesTema = almacenamiento.get(palabra);

                    // si el hastag está vacío, se crea.
                    if (mensajesTema == null){
                        almacenamiento.put(palabra,new ArrayList<>());
                        mensajesTema = almacenamiento.get(palabra);
                    }

                    mensajesTema.add(mensaje);

                }
            }

        }
        return mensajes.length; //no se que hay que devolver la verdad.
    }


    String [] colsultarTema (String tema){

        List <String> mensajes_tema = almacenamiento.get(tema);


        //Hay métodos en una sóla línea, pero mirarlo en google sería hacer trampitas.
        String [] arrayStrings = new String[mensajes_tema.size()];

        for (int i = 0; i<mensajes_tema.size(); i++){
            arrayStrings[i] = mensajes_tema.get(i);
        }
        return arrayStrings;

    }

}
