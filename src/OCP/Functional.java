package OCP;


import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Functional {

    static void primitiveFunctions() {

        //To primitives streams
        ToIntFunction<Integer> toIntFunction = value -> value;
        ToDoubleFunction<Integer> toDoubleFunction = value -> value;
        ToLongFunction<Integer> toLongFunction = value -> value;

        //var c4 = ArrayList::new;//does not compile

        //Functional interfaces with primitives , take int/double/long
        //Function take int , return R
        //Predicate take int , return boolean
        //Consumer tale int , return nothing
        //Supplier take nothing , return int
        //BinaryOperator , take 2 ints , return int
        //UnaryOperator ,take int , return int

        IntFunction<String> length = value -> value > 0 ? "Positive" : "Negative";//R apply(int value)
        //DoubleFunction...
        //LongFunction...
        IntPredicate intPredicate = value -> true;//boolean test(int value)
        IntConsumer intConsumer = value -> System.out.println(value);//void accept(int value)
        IntSupplier intSupplier = () -> 0;//int getAsInt()
        IntBinaryOperator intBinaryOperator = (left, right) -> left;//int applyAsInt(int left, int right)
        IntUnaryOperator intUnaryOperator = operand -> operand * 2;//int applyAsInt(int operand)

        //Primitives functional interfaces , take generics , return primitives

        ToIntFunction<String> toIntFunction2 = String::length;
        ToDoubleFunction<String> toDoubleFunction2 = s -> s.length();
        ToLongFunction<LocalDate> toLongFunction2 = date -> date.getDayOfMonth();
        ToIntBiFunction<String, String> addTwoS = (s, s2) -> s.length() + s.length();

        //Obviously there is no ToIntPredicate , because he already return boolean
        //and there is no ToIntConsumer , because consumer doesn't return a value
        //and there is no ToIntSupplier , because IntSupplier already return a primitive int

        //int to (long , double)
        IntToLongFunction intToLongFunction = integer -> integer * 2;
        IntToDoubleFunction intToDoubleFunction = integer -> integer / 2;

        //long to (int , double)
        LongToDoubleFunction longToDoubleFunction = Long -> Long / 2;
        LongToIntFunction longToIntFunction = Long -> (int) Long;//remember that this doesn't work without int cast

        //double to (int , long)
        DoubleToIntFunction doubleToIntFunction = Double -> (int) Double;
        DoubleToLongFunction doubleToLongFunction = Double -> (long) Double;

        BooleanSupplier booleanSupplier = () -> true;
        booleanSupplier.getAsBoolean();
    }

    static void builtInFunctions() {
        //instance methods
        Function<Integer, Integer> f = Function.identity();
        f.andThen(Function.identity());// andThen(Function after)
        f.compose(Function.identity());// andThen(Function before)
        f.apply(0);

        //class methods
        Function.identity();// Function<T, T>

        //BiFunction

        BiFunction<String, String, Integer> biFunction = (s, s2) -> s.length() + s2.length();
        biFunction.andThen(Function.identity());//  BiFunction<T, U, V> andThen(@NotNull java.util.function.Function<? super R, ? extends V> after)

        //instance
        Predicate<String> predicate = String::isBlank;
        predicate.and(predicate).or(predicate).negate();//BiPredicate take BiPredicate as Param
        predicate.test("hello");

        //Class methods (BiPredicate does not have this methods)
        Predicate.isEqual("hhh").test("eee");
        Predicate.not(predicate);//Predicate<T>

        //instance methods
        Consumer<String> consumer = System.out::println;
        consumer.andThen(consumer);// Consumer<T> andThen(@NotNull Consumer<? super T> after) , BiConsumer take BiConsumer
        consumer.accept("kkk");

        //Class methods
        //None

        //instance methods
        Supplier<String> supplier = () -> "";//Obviously , there is no BiSupplier given that Supplier does not take parameters
        supplier.get();

        //Class methods
        //None

        BinaryOperator<Integer> binaryOperator = (a, b) -> a * b;// BinaryOperator<T> extends java.util.function.BiFunction<T, T, T>
        binaryOperator.andThen(Function.identity());// BiFunction<T, U, V> andThen(@NotNull java.util.function.Function<? super R, ? extends V> after)
        binaryOperator.apply(2, 3);

        UnaryOperator<Integer> unaryOperator = UnaryOperator.identity();//public interface UnaryOperator<T> extends java.util.function.Function<T, T>
        unaryOperator.andThen(Function.identity());//after
        unaryOperator.compose(Function.identity());//before

        //Class methods
        UnaryOperator.identity();
    }

    public static void main(String[] args) {

    }

}