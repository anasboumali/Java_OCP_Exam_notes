package OCP.collections;


import java.util.*;

public class NavigableSets {

    /*
        NavigableSet
        Implementations : TreeSet

     */

    static void retrieving() {
        NavigableSet<Integer> set = new TreeSet<>(List.of(1, 3, 4, 6, 7));

        System.out.println(set.ceiling(Integer.valueOf(4)));//4 , the least element greater than or equal
        System.out.println(set.floor(Integer.valueOf(4)));//4 , the greatest less or equal

        System.out.println(set.ceiling(Integer.valueOf(5)));//6 , the least element greater than or equal
        System.out.println(set.floor(Integer.valueOf(5)));//4 , the greatest less or equal

        System.out.println(set.ceiling(Integer.valueOf(0)));//1 , the least element greater than or equal
        System.out.println(set.floor(Integer.valueOf(0)));//null

        //strictly higher or lower
        System.out.println("strictly higher or lower : ");
        System.out.println(set.higher(Integer.valueOf(4)));//6 , the least element strictly greater than
        System.out.println(set.lower(Integer.valueOf(4)));//3 , the greatest less

        System.out.println(set.higher(Integer.valueOf(0)));//1
        System.out.println(set.lower(Integer.valueOf(0)));//null
    }

    static void subSet() {
        NavigableSet<Integer> set = new TreeSet<>(List.of(1, 3, 4, 5, 6));

        /*
         * The returned set will throw an IllegalArgumentException on an attempt to insert an element outside its range.
         *
         * */
        Set<Integer> subset = set.headSet(Integer.valueOf(4));
        //subset.add(29);
        System.out.println(subset);//[1, 3] , the element is exclusive
        System.out.println(set.tailSet(Integer.valueOf(4)));//[4, 5, 6] , the element is inclusive

        System.out.println(set.headSet(Integer.valueOf(4), true));//[1, 3, 4]
        System.out.println(set.tailSet(Integer.valueOf(4), false));//[5, 6]
    }

    static void removing() {
        NavigableSet<Integer> set = new TreeSet<>(List.of(1, 3, 4, 5, 6));

        System.out.println(set.pollFirst());//1 , null if empty
        System.out.println(set.pollLast());//6 , null if empty
    }

    public static void main(String[] args) {
        //retrieving();
        subSet();
//        removing();
    }
}