package OCP;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.IntSupplier;

public class Optionals {

    static void generic() {
        //Optional<Integer> optionalOfNull = Optional.of(null);//NullPointerException
        Optional<Integer> optionalOfNullable = Optional.ofNullable(null);
        Optional<Integer> optionalOfEmpty = Optional.empty();

        System.out.println(Optional.of(2).get());//2
        //System.out.println(optionalOfNullable.get());//NoSuchElementException: No value present
        System.out.println(optionalOfNullable.orElse(2));//2
        System.out.println(optionalOfNullable.orElseGet(() -> 2));//2
        //System.out.println(optionalOfNullable.orElseThrow());//NoSuchElementException
        // System.out.println(optionalOfNullable.orElseThrow(() -> new Exception("hhhh")));//in this case , you should handle or declare
        //System.out.println(optionalOfNullable.orElseThrow(() -> {throw new IllegalStateException();}));//incorrect way to do this

        System.out.println(Optional.of(3).or(Optional::empty).orElse(18));//3
        System.out.println(Optional.empty().or(Optional::empty).orElse(18));//18
        Optional.of(2).ifPresent(System.out::println);//2
    }

    static void primitive() {
        OptionalInt optionalInt = OptionalInt.of(2);
        OptionalDouble optionalDouble = OptionalDouble.of(1 / 3.0);

        IntSupplier intSupplier = () -> 1;
        System.out.println(optionalInt.getAsInt());
        System.out.println(optionalDouble.getAsDouble());

        System.out.println(optionalInt.orElseGet(intSupplier));
        //System.out.println(optionalInt.orElseThrow());
    }

    public static void main(String[] args) throws Exception {
        primitive();
    }
}