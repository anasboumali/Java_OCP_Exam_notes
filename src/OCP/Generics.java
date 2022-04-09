package OCP;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.*;

public class Generics {

    static class MyCollection<T extends Comparable> {

        List<T> myCollection;
        int currentIndex;

        MyCollection(int capacity) {
            this.myCollection = new ArrayList<>();
        }

        public void add(T e) {
//            e.getClass();
//            e.compareTo(null);
//            e.equals();
//            e.hashCode();
//            e.toString();
//            e.notify();
//            e.notifyAll();
//            e.wait();

            myCollection.add(e);
        }

        public T get(int index) {
            return myCollection.get(index);
        }

        public <T> void puT(T index) {
            System.out.println(index);
        }
    }

    /*
     * PECS (produce extends , consume super)
     *
     * */
    static class Animal {
    }

    static class Carnivore extends Animal {
    }

    static class Lion extends Carnivore {
    }

    //PECS : Produce Extends
    static void testCovariance(List<? extends Carnivore> list) {
        //these examples doesn't compile because we didn't know the type of the list at runtime
        //if the passed list is List<Lion> , we cannot add a Carnivore in it at runtime !

        //list.add(new Carnivore());//does not compile
        //list.add(new Lion());//does not compile

        Carnivore carnivore = list.get(0);//we can get the elements because they are of Carnivore breed in all cases
        Lion lion = (Lion) list.get(0);//Ok , but dangerous as we assume that the given list is of type Lion
    }

    //PECS : Consume Super
    static void testContraVariance(List<? super Carnivore> list) {
        Animal animal = new Animal();
        Carnivore carnivore = new Carnivore();
        Lion lion = new Lion();

        //list.add(animal);//does not compile , imagine if the list passed is List<Lion> or List<Carnivore> !
        list.add(carnivore);//compile , because Carnivore > Animal
        list.add(lion);//compile , because Lion > Carnivore > Animal

        System.out.println(((Animal) list.get(0)).getClass().getName());//the good one , because the upper bound
        System.out.println(((Carnivore) list.get(0)).getClass().getName());//Ok , but risky (ClassCastException if type mismatch)
        System.out.println(((Lion) list.get(0)).getClass().getName());//Ok , but risky (ClassCastException if type mismatch)
    }

    public static void main(String[] args) {
        testCovariance(new ArrayList<Lion>(Arrays.asList(new Lion())));
        testContraVariance(new ArrayList<Carnivore>(Arrays.asList(new Lion())));
        //testContraVariance(new ArrayList<Lion>(Arrays.asList(new Lion()))); NOK




        var list = new ArrayList<String>();//wash is ArrayList<String>
        ArrayList<String> list2 = new ArrayList<>();//wash is ArrayList<String>
        ArrayList list3 = new ArrayList<String>();//list is ArrayList<Object>
        ArrayList list4 = new ArrayList();//list is ArrayList<Object>
        var list5 = new ArrayList<>();//list is ArrayList<Object>

//        List<?> list = Arrays.asList("anas" , "boumali");
//        Collections.sort( list, (o1, o2) -> o1.length() - o2.length());//does not compile because cannot infer the type on o1..use List<String>
//        System.out.println(list);
    }


}


