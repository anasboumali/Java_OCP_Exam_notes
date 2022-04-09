package OCP.collections;


import java.util.*;

public class Deques {

    /*
        Deque : short for "double ended queue" , extends Queue
        Implementations : LinkedList , ArrayDeque

     */

    static void creation() {

        Deque<Integer> queue = new LinkedList<>(List.of(1, 3, 3));
        //Deque<Integer> queue = new LinkedList<>(1);//KO does not take initial capacity

        /*This class is likely to be faster than Stack when used as a stack, and faster than LinkedList when used as a queue.
         * It's "better" in some cases because you're not allocating a node for each item to insert;
         * instead all elements are stored in a giant array, which is resized if it gets full.
         * NULL elements can be added to LinkedList but not in ArrayDeque
         *LinkedList implementation consumes more memory than the ArrayDeque
         *
         * */
        Deque<Integer> arrayDeque = new ArrayDeque<>(List.of(1, 3, 3));
    }

    static void populating() {
        Deque<Integer> deque = new LinkedList<>();

        /*Add at the front
         * throws exception when no space available
         * no special value returned
         * equivalent of Push Stack method
         *
         * */
        deque.addFirst(1);//equivalent to push , throws IllegalStateException if no space is available
        deque.addFirst(2);
        System.out.println(deque);

        /*Add at the end
         * throws exception when no space available
         * no special value returned
         * equivalent of add Queue method
         *
         * */
        deque.addLast(2);//throws IllegalStateException if no space is available
        deque.addLast(3);
        System.out.println(deque);
        deque.offerFirst(1);//true if added, false otherwise
        deque.offerLast(3);//true if added, false otherwise , equivalent of offer
    }


    static void removing() {
        Deque<Integer> deque = new LinkedList<>(List.of(1, 3));

        //remove with no exceptions(return null if empty queue)
        System.out.println(deque.pollFirst());//eq of poll, null if empty deque
        System.out.println(deque.pollLast());//remove the last element , null if empty deque

        //remove with exception(NoSuchElementException if empty deque)
        System.out.println(deque.pop());//equivalent of removeFirst()
        System.out.println(deque.removeFirst());//equivalent of pop()
        System.out.println(deque.removeLast());//equivalent of pop()
    }

    static void retrieving() {
        Deque<Integer> deque = new LinkedList<>(List.of(1, 3));

        //get element , with NoSuchElementException if empty deque
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        try {
            System.out.println(new LinkedList<>().getFirst());//throws NoSuchElementException
        } catch (Exception ex) {
            System.out.println(ex);
        }

        //get element , with null if empty deque
        System.out.println(deque.peekFirst());//equivalent of peek()
        System.out.println(deque.peekLast());
    }

    static void conversion() {
        Deque<Integer> deque = new LinkedList<>(List.of(2, 1, 3));
        System.out.println(deque);
        System.out.println(Collections.asLifoQueue(deque));
    }

    public static void main(String[] args) {
        //creation();
        //populating();
        //removing();
        //retrieving();
        conversion();
    }
}