package OCP;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Streams {

    static void primitiveFunctions() {
        //To primitives streams
        ToIntFunction<Integer> toIntFunction = value -> value;
        ToDoubleFunction<Integer> toDoubleFunction = value -> value;
        ToLongFunction<Integer> toLongFunction = value -> value;

        toIntFunction.applyAsInt(10);

        Stream.of(1, 2).mapToInt(toIntFunction);// IntStream
        Stream.of(1, 2).mapToDouble(toDoubleFunction);// DoubleStream
        Stream.of(1, 2).mapToLong(toLongFunction);// LongStream

        //Primitives Streams
        IntStream.of(1, 2).min();//OptionalInt
        IntStream.of(1, 2).max();//OptionalInt
        IntStream.of(1, 2).count();//long
        IntStream.of(1, 2).sum();//int
        IntStream.of(1, 2).average()./*OptionalDouble*/getAsDouble();//double
        IntStream.of(1, 2).boxed();//Stream<Integer>

        IntSummaryStatistics iss = IntStream.of(1, 2).summaryStatistics();//IntSummaryStatistics
        iss.getAverage();//double
        iss.getMax();//int
        iss.getMin();//int
        iss.getCount();//long
        iss.getSum();//long

        Stream.of(2, 3).mapToInt(value -> value).count();//IntStream mapToInt(ToIntFunction<? super T> mapper
        Stream.of(2, 3).mapToDouble(value -> value).count();//DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
        Stream.of(2, 3).mapToLong(value -> value).count();//LongStream mapToLong(ToLongFunction<? super T> mapper)
        //Stream.of(2, 3).mapToObj(value -> value).count();//DOES NOT COMPILE

        LongStream.of(2).mapToDouble(value -> value);// DoubleStream mapToDouble(LongToDoubleFunction mapper)
        LongStream.of(2).mapToInt(value -> (int) value);//does not compile without cast
        LongStream.of(2).mapToObj(value -> new Object());
        //LongStream.of(2).mapToLong(value -> new Object());//DOES NOT COMPILE

        Stream.of(Stream.of(2, 3), Stream.of(4, 5))
                .flatMapToInt(integerStream -> integerStream.mapToInt(value -> value))//IntStream
                .forEach(System.out::print);//2345

        Stream.of(2, 3)
                .flatMapToInt(integer -> IntStream.of(integer))//IntStream
                .average()/*OptionalDouble*/.getAsDouble();

        IntStream.of(3, 4).count();//long

        OptionalInt min = IntStream.of(3, 4).min();

        min.getAsInt();
        min.stream();//IntStream
        min.ifPresent(System.out::println);//IntConsumer
        min.orElseGet(() -> Math.abs(-3));//IntSupplier

        IntStream.of(3, 4).average().getAsDouble();//OptionalDouble

        IntStream.of(3, 4).asDoubleStream();//DoubleStream
        IntStream.of(3, 4).asLongStream();//LongStream
        IntStream.of(3, 4).sum();//int

        IntSummaryStatistics intSummaryStatistics = IntStream.of(3, 4).summaryStatistics();
        intSummaryStatistics.getSum();//long
        intSummaryStatistics.getCount();//long
        intSummaryStatistics.accept(3);//record a new value
        intSummaryStatistics.combine(new IntSummaryStatistics());

        DoubleSummaryStatistics doubleSummaryStatistics = DoubleStream.of(3).summaryStatistics();
        doubleSummaryStatistics.getSum();//double
        doubleSummaryStatistics.getMax();//double

        IntStream.of(1, 2).parallel().reduce((left, right) -> left + right).getAsInt();//reduce without identity return OptionaInt
        IntStream.of(1, 2).parallel().reduce(0, (left, right) -> left + right);//return int
    }

    static void reduce() {
        //Reducing

        BinaryOperator<Integer> accumulator = (a, b) -> Math.min(a, b);
        //accumulator should be ,an associative, non-interfering, stateless function for combining two values
        //return Optional<T>
        Stream.of(1, 2).reduce(accumulator);

        //With identity
        Stream.of(1, 2).reduce(0 /*T identity*/, accumulator);

        //With combiner
        BiFunction<Integer, String, Integer> lenAccumulator = (len, name) -> len + name.length();
        BinaryOperator<Integer> lenCombiner = Math::addExact;
        Stream.of("anas", "fatiha").reduce(0, lenAccumulator, lenCombiner);
    }

    static void collectors() {
        //Collectors

        /* A Collector is specified by four functions that work together to accumulate entries
        into a mutable result container, and optionally perform a final transform on the result. They are :

        creation of a new result container (supplier())
        incorporating a new data element into a result container (accumulator())
        combining two result containers into one (combiner())
        performing an optional final transform on the container (finisher())

        Collectors also have a set of characteristics, such as Collector.Characteristics.CONCURRENT,
        that provide hints that can be used by a reduction implementation to provide better performance.
        */

        Supplier<List> container = ArrayList::new;
        BiConsumer<List, Integer> listAccumulator = List::add;
        BiConsumer<List, List> listCombiner = List::addAll;

        Stream.of(1, 2).collect(container, listAccumulator, listCombiner);

        //Built in Collectors :

        //List :

        Stream.of("Anas", "fatiha", "hamza").collect(Collectors.toList());//List<String>

        //Unmodifiable List (null values will result in NullPointerException)
        var unmodifiableList = Stream.of("Anas", "fatiha", "hamza").collect(Collectors.toUnmodifiableList());//Set<String>

        //unmodifiableList.clear();//UnsupportedOperationException

        //Set
        var set = Stream.of("Anas", "fatiha", "hamza").collect(Collectors.toSet());//Set<String>
        System.out.println(set.getClass().getName());//HashSet

        //Unmodifiable Set
        var unmodifiableSet = Stream.of("Anas", "fatiha", "hamza").collect(Collectors.toUnmodifiableSet());//Set<String>
        System.out.println(unmodifiableSet.getClass().getName());//ImmutableCollections$SetN

        //unmodifiableSet.add("kk");//UnsupportedOperationException

        //Unmodifiable map (key and values must be non null, duplicate keys results in IllegalStateException
        //unless a good merge function is used
        //Use ToMap if you want a mutable map

        Function<String, Integer> keyMapper = String::length;
        Function<String, String> valueMapper = Function.identity();
        BinaryOperator<String> mergeFunction = (key1, key2) -> key1;
        var unmodifiableMap = Stream.of("Anas", "Anas", "fatiha", "hamza").collect(Collectors.toUnmodifiableMap(
                keyMapper,
                valueMapper,
                mergeFunction//Optional
        ));//Map<Integer, String>

        System.out.println(unmodifiableMap);
        System.out.println(unmodifiableMap.getClass().getName());//ImmutableCollections$MapN
        //unmodifiableMap.put(4 , "Karim");//UnsupportedOperationException

        //Grouping
        Function<String, Integer> classifier = String::length;
        var groupingMap = Stream.of("anas", "karim", "fatiha", "hamza").collect(Collectors.groupingBy(
                classifier,
                Collectors.maxBy(String::compareTo)//take a Comparator
        ));//Map<Integer, List<String>>
        System.out.println(groupingMap);//without maxBy {4=[Anas, kari], 5=[hamza], 6=[fatiha]}
        System.out.println(groupingMap);//with maxBy {4=Optional[kari], 5=Optional[hamza], 6=Optional[fatiha]}
        System.out.println(groupingMap.getClass().getName());//HashMap

        var max = Stream.of("anas", "karim", "fatiha", "hamza").collect(Collectors.maxBy(
                String::compareTo
        ));

        var min = Stream.of("anas", "karim", "fatiha", "hamza").collect(Collectors.minBy(
                String::compareTo
        ));

        var sum = Stream.of("anas", "karim", "fatiha", "hamza").collect(Collectors.summingInt(String::length));


    }

    static void basicOperations() {
        //Distinct
        Stream.of(1, 2).distinct();//remove duplicates

        //Statistics
        Stream.of(1, 2).min(Comparator.naturalOrder());//Optional<T>
        Stream.of(1, 2).max(Comparator.naturalOrder());//Optional<T>
        Stream.of(1, 2).count();//long

        //Sort
        Stream.of(1, 2).sorted();// must implements Comparable , ClassCastException otherwise
        Stream.of(1, 2).sorted(Comparator.naturalOrder());// with Comparator

        Stream.of(1, 2).findFirst();//return Optional<T> , get the first element , if unordered , any element may be returned
        Stream.of(1, 2).findAny();//return Optional<T>  , nondeterministic; it is free to select any element in the stream

        //Get List
        Stream.of(1, 2).toList();//immutable List<T>
    }

    public static void main(String[] args) {

    }

}