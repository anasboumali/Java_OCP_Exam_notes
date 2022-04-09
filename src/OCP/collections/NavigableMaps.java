package OCP.collections;


import java.util.*;

public class NavigableMaps {

    /*
        NavigableMap
        Implementations : TreeMap

     */

    static void retrieving() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("anas", 3, "hamza", 9, "karim", 10));

        System.out.println(treeMap.ceilingKey("hamza"));//anas
        System.out.println(treeMap.floorKey("hamza"));//hamza

        System.out.println(treeMap.ceilingKey("imad"));//karim
        System.out.println(treeMap.floorKey("badr"));//anas


        System.out.println(treeMap.ceilingEntry("imad"));//karim=10
        System.out.println(treeMap.floorEntry("badr"));//anas=3

        //strictly higher or lower
        System.out.println(treeMap.higherKey("hamza"));//karim
        System.out.println(treeMap.lowerKey("hamza"));//anas

        System.out.println(treeMap.higherEntry("hamza"));//karim=10
        System.out.println(treeMap.lowerEntry("hamza"));//anas=3
    }

    static void subSet() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("anas", 3, "hamza", 9, "karim", 10));

        System.out.println(treeMap.subMap("anas ", false, "karim", true));//{hamza=9, karim=10}
        System.out.println(treeMap.headMap("hamza ", true));//{anas=3, hamza=9}
        System.out.println(treeMap.tailMap("anas ", false));//{hamza=9, karim=10}

        System.out.println(treeMap.descendingMap());//{karim=10, hamza=9, anas=3}
        System.out.println(treeMap.descendingKeySet());//[karim, hamza, anas]
    }

    static void removing() {
        NavigableMap<String, Integer> treeMap = new TreeMap<>(Map.of("anas", 3, "karim", 10, "hamza", 9));

        System.out.println(treeMap.pollFirstEntry());//anas=3
        System.out.println(treeMap.pollLastEntry());//karim=10
    }

    public static void main(String[] args) {
        //retrieving();
        subSet();
        //removing();
    }
}