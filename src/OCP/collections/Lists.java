package OCP.collections;


import javax.sound.midi.Soundbank;
import java.net.Inet4Address;
import java.sql.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lists {


    static class Person implements Comparable<Person> {
        String name;

        Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Person o) {//if no generic type is provided , compareTo take Object
            return name.compareTo(o.name);
        }
    }

    /*

        Implementations : ArrayList ,LinkedList ( legacy : Vector , Stack )

     */
    static void creation() {
        List<Integer> list = new ArrayList<>();
        List<Integer> listWithCapacity = new ArrayList<>(2);
        List<Integer> listFromList = new ArrayList<>(list);

//        List<Integer> listFromNullKO = List.of(1 , null);//NullPointerException
        List<Integer> listFromBuilderArray = List.of(new Integer[]{1,2,3});//unmodifiable list (immutable)
        List<Integer> listFromBuilder = List.of(1, 3, 2);//unmodifiable list (immutable)
        List<Integer> listCopyOfList = List.copyOf(list);////unmodifiable list (immutable)

        List<Integer> listFromArray = Arrays.asList(new Integer[]{1, 2});//mutable list
        List<Integer> listArraysAsList = Arrays.asList(1, 3, 4);

        //from Collections builder

        List<Integer> emptyList = Collections.EMPTY_LIST;//Immutable , unchecked assignment
        List<Integer> emptyList2 = Collections.emptyList();//Immutable
        List<Integer> singletonList = Collections.singletonList(1);//Immutable
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);//unmodifiable view
        List<Integer> origin = new ArrayList<>(List.of(1,2));
        List<Integer> unmodifiableListTestBackedList = Collections.unmodifiableList(list);//unmodifiable view
        origin.clear();
        System.out.println(unmodifiableListTestBackedList);

        //from streams

        List<Integer> listFromStreamImmutable = Stream.of(1, 3).toList();//Immutable
        List<Integer> listFromStreamMutable = Stream.of(1, 3).collect(Collectors.toList());


        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>(List.of(1, 2, 3)));
        for (var e : synchronizedList) {
            synchronizedList.remove(e);//ConcurrentModificationException
        }
    }

    static void populating() {
        List<Integer> list = new ArrayList<>(0);
        System.out.println(list.add(1));//true , exceeding capacity des not harm

        list.add(0, 2);//void method , [2, 1]

        list.addAll(List.of(3, 4));//return boolean , [2, 1, 3, 4]
        list.addAll(2, List.of(19, 20));//[2, 1, 19, 20, 3, 4]

        //with Collections.copy()

        List<Integer> source = List.of(1, 3, 4);
        List<Integer> destKO = new ArrayList<>();//IndexOutOfBoundsException : Source does not fit in dest
        List<Integer> destOK = new ArrayList<>(List.of(6, 7, 8));//IndexOutOfBoundsException : Source does not fit in dest

        Collections.copy(destOK, source);
        System.out.println(destOK);//[1, 3, 4]
    }

    static void replacing() {

        List<Integer> list = new ArrayList<>(List.of(1, 2));
        list.set(1, 3);//will return 2 (previously element)
        System.out.println(list);//[1, 3]

        UnaryOperator<Integer> multiplication = a -> a * 2;
        list.replaceAll(multiplication);//void method
        System.out.println(list);//[2, 6]

        List<Integer> listToFill = new ArrayList<>(List.of(1, 3, 4));
        Collections.fill(listToFill, 1);//[1, 1, 1]
        System.out.println(listToFill);
    }

    static void removing() {
        List<Integer> list = new ArrayList<>(List.of(1, 2));

        list.remove(Integer.valueOf(1));//remove the first occurrence , return true if the element is found
        System.out.println(list);//[2]

        Integer removed = list.remove(0);//return the removed element
        System.out.println(removed);//2

        List<Integer> list2 = new ArrayList<>(List.of(1, 2, 3, 4));
        list2.retainAll(List.of(2, 3, 5));//Intersection operation , return true if the elements are changed
        System.out.println(list2);//[2, 3]

        list.removeIf(Predicate.not(integer -> integer > 0));
    }

    static void retrieving() {
        List<Integer> list = new ArrayList<>(List.of(3, 3, 5, 6));
        list.get(0);//3

        System.out.println(list.contains(Integer.valueOf(3)));//true , contains take Object as parameter and call equals

        System.out.println(list.containsAll(List.of(3, 5, 6)));//true
        System.out.println(list.indexOf(Integer.valueOf(3)));//0 , index of the first occurrence
        System.out.println(list.lastIndexOf(Integer.valueOf(3)));//1
    }

    static void sorting() {

        List<Person> persons = new ArrayList<>(List.of(new Person("anas"), new Person("fatiha")));

        Collections.sort(persons);//does not compile if Person does not implement Comparable ,
        // throws UnsupportedOperationException if immutable list
        System.out.println(persons);//[Person{name='anas'}, Person{name='fatiha'}]


        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.compareTo(o1);
            }
        });
        Collections.sort(persons, (o1, o2) -> o2.compareTo(o1));
        Collections.sort(persons, Comparator.reverseOrder());//Be aware , this is not a method reference , is a built in comparator

        //all the above three are equivalent

        System.out.println(persons);//[Person{name='fatiha'}, Person{name='anas'}]
        //don't forget to override toString() to see this output


        //natural order
        Collections.sort(persons, Comparator.naturalOrder());

        //reverse order
        Collections.sort(persons, Comparator.reverseOrder());


        //comparing with key extractor and natural order
        Collections.sort(persons, Comparator.comparing(person -> person));

        //comparing with key extractor & comparator
        Function<Person, String> keyExtractor = Person::getName;
        Comparator<String> comparator = String::compareTo;
        Collections.sort(persons, Comparator.comparing(keyExtractor, comparator));


        //null-friendly first comparator
        Collections.sort(persons, Comparator.nullsFirst(Comparator.naturalOrder()));

        //null-friendly last comparator
        Collections.sort(persons, Comparator.nullsLast(Comparator.naturalOrder()));

        //primitives comparator
        ToIntFunction<Person> lengthExtractorInt = value -> value.getName().length();
        ToLongFunction<Person> lengthExtractorLong = value -> value.getName().length();
        ToDoubleFunction<Person> lengthExtractorDouble = value -> value.getName().length();

        Collections.sort(persons, Comparator.comparingInt(lengthExtractorInt));
        Collections.sort(persons, Comparator.comparingLong(lengthExtractorLong));
        Collections.sort(persons, Comparator.comparingDouble(lengthExtractorDouble));
    }

    static void binarySearch() {

        /*
         *
         * Warning : the elements should be sorted in ascending order
         *
         * */


        List<Integer> list = List.of(1, 3, 6, 8, 5);
        //the list element should implement Comparable
        System.out.println(Collections.binarySearch(list, 6));//2 , the index of the key , otherwise : -(insertion point) - 1.
        System.out.println(Collections.binarySearch(list, 4));//-3 , the index of the key , otherwise : -(insertion point) - 1.

        System.out.println(Collections.binarySearch(List.of(new Person("anas"), new Person("hakim")),
                new Person("anas")));//0

        //with Comparator
        System.out.println(Collections.binarySearch(List.of(1, 2), 1, Comparator.comparingInt(value -> value)));

    }

    static void statistics() {
        Collections.max(List.of(1, 3, 4));
        Collections.min(List.of(1, 3, 4));
        Collections.min(List.of(1, 3, 4), Comparator.naturalOrder());

        Collections.frequency(List.of(1, 1, 3, 4, 1), 1);//3
    }

    static void setOperations() {
        //swap
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
        Collections.swap(list, 0, 2);
        System.out.println(list);//[3, 2, 1]

        //reverse
        Collections.reverse(list);
        System.out.println(list);//[1, 2, 3]

        //rotate
        Collections.rotate(list, -1);
        System.out.println(list);//[2, 3, 1]

        //shuffle
        Collections.shuffle(list);
        System.out.println(list);//[1, 2, 3]

        //equals
        System.out.println(List.of(1, 3, 4).equals(List.of(1, 3, 4)));//true
        System.out.println(List.of(1, 3, 8).equals(List.of(1, 3, 4)));//false

        //disjoint
        System.out.println(Collections.disjoint(List.of(1, 2, 3), List.of(7, 8)));//true if they have no element in common
        System.out.println(Collections.disjoint(List.of(1, 2, 3), List.of(7, 2)));//false
    }

    static void subLists() {

        System.out.println(List.of(1, 3, 4, 7).subList(1, 3));//[3,4]
        System.out.println(List.of(1, 3, 4, 7).subList(1, 1));//[]

        System.out.println(Collections.indexOfSubList(List.of(1, 3, 5, 6, 8, 5, 6), List.of(5, 6)));//2 , first occurence
        System.out.println(Collections.indexOfSubList(List.of(1, 3, 5, 6, 8, 5, 6), List.of(5, 8)));//-1

        System.out.println(Collections.lastIndexOfSubList(List.of(1, 3, 5, 6, 8, 5, 6), List.of(5, 6)));//5
    }

    static void conversion() {
        //to array
        Integer[] array =(Integer[]) List.of(1, 3, 4).toArray();//to objet array
        System.out.println(Arrays.toString(array));

        //to array
        Integer[] bigEnough = new Integer[3];
        Integer[] noFit = new Integer[1];

        Integer[] arrBigEnough = List.of(2, 3).toArray(bigEnough);//the array is big enough
        Integer[] newArray = List.of(2, 3).toArray(noFit);//the array is not big enough , create & return new array

        System.out.println(Arrays.toString(bigEnough));//[2, 3, null]
        System.out.println(Arrays.toString(noFit));//[null]
        System.out.println(Arrays.toString(newArray));//[2, 3]
    }

    public static void main(String[] args) {
        creation();
        //populating();
        //replacing();
        //removing();
        //retrieving();
        //sorting();
        //binarySearch();
        //statistics();
        //setOperations();
        //subLists();
        //conversion();
    }

}