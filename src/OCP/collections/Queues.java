package OCP.collections;


import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queues {

    /*

        Implementations : LinkedList , PriorityQueue , ArrayDeque

     */

    static void creation() {
        Queue<Integer> queue = new LinkedList<>(List.of(1, 3, 3));

        Queue<Integer> priorityQueueWithCapacity = new PriorityQueue<>(1);
        Queue<Integer> priorityQueueWithCapacityAndComparator = new PriorityQueue<>(1, Comparator.naturalOrder());
        Queue<Integer> priorityQueueWithComparator = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> priorityQueueWithSortedSet = new PriorityQueue<>(Collections.emptySortedSet());
        Queue<Integer> priorityQueueWithList = new PriorityQueue<>(Collections.emptyList());
    }

    static void populating() {
        Queue<Integer> queue = new PriorityQueue<>(1);//IllegalArgumentException if initialCapacity is less than 1
        queue.add(1);//true
        queue.add(2);//IllegalStateException if the element cannot be added at this time due to capacity restrictions

        queue.offer(5);//add without violating capacity restrictions , true if added ,false otherwise
    }


    static void removing() {
        Queue<Integer> queue = new LinkedList<>(List.of(1));

        System.out.println(queue.poll());//1 , retrieve element , null if the queue is empty
        System.out.println(queue.poll());//null

        try {
            System.out.println(queue.remove());//NoSuchElementException if empty queue
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        }
    }

    static void retrieving() {
        Queue<Integer> queue = new LinkedList<>(List.of(1));
        System.out.println(queue.peek());//1 , retrieve but does not remove

        try {
            System.out.println(new LinkedList<>().element());//NoSuchElementException if empty queue
        } catch (NoSuchElementException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        //creation();
        //populating();
        //removing();
        //retrieving();
        //conversion();
    }

}