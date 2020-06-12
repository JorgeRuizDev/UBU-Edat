package pr09;

import java.lang.invoke.WrongMethodTypeException;
import java.util.*;

public class ArbolTabulado <E> extends AbstractMap <E,E> {
    private Map <E,E> MAPA;

    private E RAIZ = null;

    public ArbolTabulado(){
        MAPA = new HashMap<>();
    }



    @Override
    public Set<Entry<E, E>> entrySet() {
        return MAPA.entrySet();
    }

    @Override
    public E put(E hijo, E padre){

        //Si se introduce el primer elemento
        if (RAIZ == null){
            if (padre != null)
                throw new IllegalArgumentException("No se ha introducido una raiz");

            RAIZ = hijo;
        }


        if (MAPA.containsKey(hijo))
            throw new IllegalArgumentException("No puede haber dos elementos iguales");
        if (hijo.equals(padre))
            throw new IllegalArgumentException("Un padre no puede ser su propio hijo");

        return MAPA.put(hijo,padre);
    }


    public E remove (Object o ){

        if ( ! o.getClass().equals(RAIZ.getClass()))
           throw new WrongMethodTypeException();

        if (size() == 1) {
            E devolver = RAIZ;
            clear();
            RAIZ = null;
            return devolver;
        }

        if (o.equals(RAIZ)){
            E devolver = RAIZ;
            removeRoot();
            MAPA.remove(o);
            return devolver;
        }
        else{

            E padre = null;

            for (Map.Entry<E,E> entrada : entrySet()){
                if (entrada.getKey().equals(o)) {
                    if (padre == null)
                        padre = entrada.getValue();
                }
            }

            for ( Map.Entry<E,E> entrada : entrySet()){
                if (entrada.getValue() != null && entrada.getValue().equals(o))
                    entrada.setValue(padre);
            }
        }



        return MAPA.remove(o);

    }


    private void removeRoot(){
        E nuevaRaiz = null;
        E viejaRaiz = RAIZ;
        for (Map.Entry<E,E> entrada: entrySet() ){
            if (entrada.getValue() != null && entrada.getValue().equals(RAIZ) && nuevaRaiz == null) {
                nuevaRaiz = entrada.getKey();
                entrada.setValue(null);
                RAIZ = nuevaRaiz;
            }
            else if (entrada.getValue() != null && entrada.getValue().equals(viejaRaiz)){
                entrada.setValue(nuevaRaiz);
            }
        }


    }
    public List<E> getDescendants(E padre) {

        Set <E> descendientes = new HashSet<E>();

        getDescendantsRecursive(descendientes,padre);

        List<E> lista_descendientes = new ArrayList<>(descendientes.size());

        lista_descendientes.addAll(descendientes);

        return lista_descendientes;
    }
    private void getDescendantsRecursive(Set <E> lista, E padre){

        int nElementosEncontrados = 0;

        for (Entry <E,E> entrada : entrySet()){
            if (entrada.getValue() != null && entrada.getValue().equals(padre) && entrada.getKey() != null) {
                if (lista.add(entrada.getKey()))
                    nElementosEncontrados++;
            }
        }
        if (nElementosEncontrados == 0) //Si E padre es una hoja:
            return;

        Object [] array = lista.toArray();

        for (Object elemento:  array)
            getDescendantsRecursive(lista,(E) elemento);
    }

    public List<E> getAncestors(E set) {
        Set <E> ancestros = new HashSet<>();

        getAncestorsRecursive(ancestros,set);

        List <E> lsita = new ArrayList<>(ancestros);
        return lsita;
    }

    private void getAncestorsRecursive(Set <E> lista, E hijo){

        if (hijo == RAIZ)
            return;

        int nPadres = 0;


        for (Map.Entry<E,E> entrada : entrySet()){
            if (entrada.getKey().equals(hijo)){
                if (lista.add(entrada.getValue()))
                     nPadres++;
            }
        }

        if (nPadres == 0)
            return;

        Set <E> clon_lista = new HashSet<E> (lista);
        for (E elemento : clon_lista)
            getAncestorsRecursive(lista, elemento);

    }

    public List<E> breadthTraverse() {
        List <E> recorrido = new ArrayList<>(this.size());
        List <E> nivel = new LinkedList<>();

        nivel.add(RAIZ);

        breadthTraverseRecursive(recorrido, nivel);


        return new ArrayList<E>(recorrido);
    }
    public void breadthTraverseRecursive(List<E> recorrido, List <E> nivel){

        List <E> siguiente_nivel = new LinkedList<>();

        for (E elem : nivel){
            recorrido.add(elem);
            for (Map.Entry<E,E> entrada : entrySet()){
                if (entrada.getValue() != null && entrada.getValue().equals(elem))
                    siguiente_nivel.add(entrada.getKey());
            }
        }


        if (siguiente_nivel.size() == 0)
            return;

        breadthTraverseRecursive (recorrido, siguiente_nivel);
    }
    private E getRaiz(){
        return null;
    }

    public int height(E treeSet) {
        return height_recursive(treeSet);
    }
    private int height_recursive(E elemento){
        List <E> hijos = new LinkedList<>();
        int altura = 0;
        for (Map.Entry<E,E> entrada : entrySet()){
            if (entrada.getValue() != null && entrada.getValue().equals(elemento)){
                hijos.add(entrada.getKey());
            }
        }

        //Si es hoja
        if (hijos.size() == 0){
            return 0;
        }

        for (E hijo : hijos){
            int altura_hijo = height_recursive(hijo);
            if (altura_hijo > altura)
                altura = altura_hijo;
        }

        return altura +1;
    }
    public int depth(E treeSet) {
        int profundidad = 0;
        return 0;
    }

    @Override
    public int size(){
        return MAPA.size();
    }

    public void clear(){
        MAPA.clear();
    }

}
