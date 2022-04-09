package OCP;


import java.lang.annotation.Repeatable;
import java.time.format.FormatStyle;

@interface Donation {
    String[] value();
}

@interface Gifts {
    Gift[] value();
    String info() default "";
}

@Repeatable(Gifts.class)
@interface Gift {
    String value() default "";

    Class clazz() default Annotations.class;

    FormatStyle format() default FormatStyle.FULL;

    Donation donation() default @Donation({"charity", "humanity"});

    int constant = 10;
}

@Gift(value = "anas", clazz = String.class, donation = @Donation("charity"))
public class Annotations {

    @Gift
    @Gift("anas")
    @Gift(donation = @Donation({}))
    public static void main(String[] args) throws Exception {


    }
}