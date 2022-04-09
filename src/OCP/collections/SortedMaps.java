package OCP.collections;


import com.sun.source.tree.Tree;

import java.util.*;

public class SortedMaps {

    /*
        SortedMap
        Implementations : TreeMap

     */

    static void creation() {
        SortedMap<String, Integer> treeMap = new TreeMap<>(Map.of("anas", 3, "hamza", 9));
        SortedMap<String, Integer> treeMapWithComparator = new TreeMap<>(Comparator.naturalOrder());
    }

    static void retrieving() {
        SortedMap<String, Integer> treeMap = new TreeMap<>(Map.of("anas", 3, "hamza", 9));

        System.out.println(treeMap.firstKey());//anas
        System.out.println(treeMap.lastKey());//hamza
    }

    static void subMap() {
        SortedMap<String, Integer> treeMap = new TreeMap<>(Map.of("anas", 3, "hamza", 9, "karim", 10));

        System.out.println(treeMap.subMap("hamza", "karim"));//{hamza=9} , karim is exclusive the returned map is backed by the original
        System.out.println(treeMap.headMap("hamza"));//{anas=3} , hamza is exclusive
        System.out.println(treeMap.tailMap("hamza"));//{hamza=9, karim=10} , karim is inclusive
    }

    public static void main(String[] args) {
        //creation();
        //retrieving();
        //subMap();
    }
}