package Test;

class A {
    static {
        System.out.println("Base Static");
    }

     {
        System.out.println("Base instance");
    }
    A() {
        System.out.println("Base contructor");
    }
}
public class Test  extends A{
    static {
        System.out.println("Child Static");
    }

    {
        System.out.println("Child instance");
    }

    Test()
    {super();
        System.out.println("Child Constructor");
    }

    public static void main(String[] stars) {
        new Test();
    }
}