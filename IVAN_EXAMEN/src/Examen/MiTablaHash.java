package Examen;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MiTablaHash<K,V> /*extends AbstractMap<K,V>*/ {


    /*
        Hay que implementarlo?

        pues no lo se...


        Respuesta:
        AbstractMap requiere de una clase que sea Entry, no entrada, no sería valido.

    @Override
    public Set<Entrada<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        set.addAll(tabla);
        return
    }

     */

    public Set<Entrada<K,V>> entrySet(){
        //Es un addALl camuflado.
        return new HashSet<>(tabla);
    }


    private class Entrada <K,V> {
        private K clave;
        private V valor;
        boolean borrado;

        /*
         * Autogenerados
         *
         */
        public boolean isBorrado() {
            return borrado;
        }

        public void setBorrado(boolean borrado) {
            this.borrado = borrado;
        }

        public K getClave() {
            return clave;
        }

        public void setClave(K clave) {
            this.clave = clave;
        }

        public V getValor() {
            return valor;
        }

        public void setValor(V valor) {
            this.valor = valor;
        }



    }

    private ArrayList<Entrada<K,V>> tabla;

    public MiTablaHash (int tamaño){
        tabla = new ArrayList<>(tamaño);

        for (int i = 0; i <tamaño; i++){
            //los valores internos serán false, null y null;
            tabla.set(0,new Entrada<>());
        }
    }

    public V put (K key, V value){


        return null;
    }




}
