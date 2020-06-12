package ColaPrioritaria;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        PriorityQueue<Integer> enteros = new PriorityQueue<>();
        List<Integer> insertados = Arrays.asList(23,4,78,2,19,25,89);
        enteros.addAll(insertados);

        System.out.println(enteros);

        enteros.clear();
        insertados = Arrays.asList(23,4,78,2,19,25,89);
        enteros.addAll(insertados);
        System.out.println(enteros);
        while(!enteros.isEmpty())
            System.out.print(enteros.poll()+", ");

        insertados = Arrays.asList(23,4,78,2,19,25,89);
        enteros.clear();
        enteros.addAll(insertados);
        enteros.remove(23);
        System.out.println(enteros);

        enteros.clear();
        Arrays.asList(23,4,78,2,19,25,89);
        enteros.addAll(insertados);
        System.out.println(enteros);

        PriorityQueue<Integer> enteros2 = new PriorityQueue<>();
        insertados = Arrays.asList(68,86,78,8,18,59,52,75,48,59);

        enteros2.addAll(insertados);
        System.out.println(enteros2);
        for (int i = 0; i<3; i++) {
            enteros2.poll();
            System.out.println(enteros2);
        }
    }



}
