package OCP;


import java.util.*;

interface A {
    default void print() {
        System.out.println("interface A");
    }
}

abstract class AC {
    protected abstract void print();
}

class C extends AC implements A {


    @Override
    public void print() {

    }
}


public class OCP {

    private static void add(Object obj) {
        System.out.println("add object");
    }

    private static void add(byte b) {
        System.out.println("add byte");
    }

    private static void add(short b) {
        System.out.println("add byte");
    }

    static class Base {
        void method(Collection collection) {
            System.out.println("Base Collection");
        }
    }

    static class Sub extends Base {
        void method(Collection collection) {
            System.out.println("Sub Collection");
        }

        void method(List list) {
            System.out.println("Sub List");
        }
    }


    public static void main(String[] args) throws Exception {
        byte b = 19;
        short s = 9;
        add(b + s);

        Base base = new Base();
        Base baseSub = new Sub();
        Sub sub = new Sub();

        List<Integer> list = new ArrayList<>();

        base.method(list);//Base Collection
        baseSub.method(list);//Sub Collection
        sub.method(list);//Sub List
    }
}