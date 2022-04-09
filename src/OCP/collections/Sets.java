package OCP.collections;


import java.util.*;

public class Sets {

    /*
        IMPORTANT : Java designers had forgotten to add get(element) method to get an element
        Set
        Implementations : HashSet , LinkedHashSet

     */

    static void creation() {
        Set<Integer> set = new HashSet<>(List.of(1, 3, 3 , null));//allow null values

        /*
         * HashSet is more performant than LinkedHashSet
         * LinkedHashSet maintains insertion order
         *
         * */

        Set<Integer> linkedHashSet = new LinkedHashSet<>(List.of(1, 3, 3));
        Set<Integer> EMPTY_SET = Collections.EMPTY_SET;
        Set<Integer> emptySet = Collections.emptySet();
        Set<Integer> synchronizedSet = Collections.synchronizedSet(set);
    }

    static void populating() {
        Set<Integer> set = new HashSet<>(List.of(1, 3, 3));
        System.out.println(set.add(3));//false if the key already exists

        set.addAll(List.of(1, 2, 4));
        System.out.println(set);//1, 2, 3, 4]
    }


    static void removing() {
        Set<Integer> set = new HashSet<>(List.of(1, 3, 3));
        set.remove(3);//O(1) constant time
    }

    static void retrieving() {
        Set<Integer> set = new HashSet<>(List.of(1, 3, 3));
        set.contains(1);//O(1) constant time
    }

    public static void main(String[] args) {
        //creation();
        populating();
        //removing();
        //retrieving();
    }
}