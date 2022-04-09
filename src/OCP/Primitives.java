package OCP;

public class Primitives {

    static void bounds() {

        System.out.println("Bytes");
        System.out.println(Byte.MAX_VALUE);//127
        System.out.println(Byte.MIN_VALUE);//-128

        System.out.println("Character");
        System.out.println((int) Character.MAX_VALUE);//65535
        System.out.println((int) Character.MIN_VALUE);//0

        System.out.println("Short");
        System.out.println(Short.MAX_VALUE);//32767
        System.out.println(Short.MIN_VALUE);//-32768

        System.out.println("Integer");
        System.out.println(Integer.MAX_VALUE);//2 147 483 647
        System.out.println(Integer.MIN_VALUE);//2 147 483 647

        System.out.println("Long");
        System.out.println(Long.MAX_VALUE);//9 223 372 036 854 775 807
        System.out.println(Long.MIN_VALUE);//-9223372036854775808

        System.out.println("Float");
        System.out.println(Float.MAX_VALUE);//3.4028235E38
        System.out.println(Float.MIN_VALUE);//1.4E-45

        System.out.println("Double");
        System.out.println(Double.MAX_VALUE);//1.7976931348623157E308
        System.out.println(Double.MIN_VALUE);//4.9E-324
    }

    public static void main(String[] args) {


        bounds();

        byte b = 9;
        char c = 8;
        short s = 0;
        int i = 2;
        long l = 3;
        float f = 7;
        double d = 3;


        //anything to char
        final byte fb = '?';
        s *= i;

        //anything to char(need explicit cast)


        //anything to int
        i = b;
        i = c;
        i = s;
        i = (int) l;
        i = (int) f;
        i = (int) d;

        l = b;
        l = c;
        l = s;
        l = i;

        f = b;
        f = c;
        f = i;


        d = b;
        d = c;
        d = f;


        c = (char) i;
        c *= i * i;

    }
}