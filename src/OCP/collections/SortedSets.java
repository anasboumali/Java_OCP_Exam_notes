package OCP.collections;


import com.sun.source.tree.Tree;

import java.util.*;

public class SortedSets {

    /*
        SortedSet
        Implementations : TreeSet

     */

    static void creation() {
        SortedSet<Integer> treeSetFromCollection = new TreeSet<>(List.of(1, 3, 3, 5, 4, 6));
        System.out.println(treeSetFromCollection);//[1, 3, 4, 5, 6]
        SortedSet<Integer> treeSetWithComparator = new TreeSet<>(Comparator.naturalOrder());
    }

    static void retrieving() {
        SortedSet<Integer> set = new TreeSet<>(List.of(1, 3, 4, 5, 6));

        System.out.println(set.first());//1
        System.out.println(set.last());//6
    }

    static void subSet() {
        SortedSet<Integer> set = new TreeSet<>(List.of(1, 3, 4, 5, 6));

        //Warning : subSet method take Elements and not indices
        SortedSet<Integer> subSet = set.subSet(Integer.valueOf(1), Integer.valueOf(5));//backed by set
        System.out.println(subSet);//[1, 3, 4]
        subSet.clear();
        System.out.println(set);//[5, 6] !!!, because the subSet is backed by set

        set.addAll(List.of(1, 3, 4));

        System.out.println(set.headSet(Integer.valueOf(4)));//[1, 3] , the element is exclusive
        System.out.println(set.tailSet(Integer.valueOf(4)));//[4, 5, 6] , the element is inclusive

        System.out.println(set.headSet(Integer.valueOf(0)));//[]
        System.out.println(set.headSet(Integer.valueOf(-1)));//[]
        System.out.println(set.headSet(Integer.valueOf(7)));//[1, 3, 4, 5, 6]

        System.out.println(set.tailSet(Integer.valueOf(-1)));//[1, 3, 4, 5, 6]
        System.out.println(set.tailSet(Integer.valueOf(6)));//[6]
        System.out.println(set.tailSet(Integer.valueOf(7)));//[]
    }

    public static void main(String[] args) {
        //creation();
        //retrieving();
        //subSet();
    }
}