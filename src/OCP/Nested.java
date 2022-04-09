package OCP;

class Outer {
    private int field = 10;
    private static int staticField = 20;
    final private int constant = 20;

    private void localInnerClass() {

        class local //no access modifier is allowed nor static modifier except final and abstract
        {
            private int localField = constant;

            private void doSomething() {
                var v = field;
                var sv = staticField;
                Outer.this.new Inner().doSomething();
                new Outer.StaticInner().doSomething();
            }

        }

    }

    private void method() {
        System.out.println("outer method");
        Inner inner = new Inner();

        inner.innerField = 19;
        inner.doSomething();

        StaticInner staticInner = new StaticInner();
        staticInner.doSomething();
    }

    static private void staticMethod() {
        System.out.println("static outer method");
        //Inner inner = new Inner();//cannot reference from static context
        Inner inner = new Outer().new Inner();

        StaticInner staticInner = new StaticInner();
        staticInner.doSomething();
    }

    class Inner {
        private int field = 30;
        private int innerField;

        Inner() {
        }

        private void doSomething() {
            field = 17;
            Outer.this.field = 30;
            Outer.this.method();
            Outer.staticField = 19;

            Inner inner = new Inner();
            Inner innerFromOuter = Outer.this.new Inner();
            inner.doSomething();

            StaticInner staticInner = new StaticInner();
            staticInner.doSomething();
        }
    }

    static class StaticInner {
        private int field = 30;
        private int innerField;
        private int staticField = 9;

        StaticInner() {
        }

        private void doSomething() {
            field = 17;
            //Outer.this.field = 30;//cannot access outer instance fields
            //Outer.this.method();/cannot access outer instance fields
            Outer.staticField = 19;
            staticField = 10;
        }
    }

}

public class Nested {

    public static void main(String[] args) {

        Outer.Inner inner = new Outer().new Inner();
        Outer.StaticInner staticInner = new Outer.StaticInner();
        //Outer.StaticInner staticInner2 = new Outer().new StaticInner();

    }
}