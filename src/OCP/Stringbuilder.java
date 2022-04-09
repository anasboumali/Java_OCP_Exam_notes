package OCP;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Stringbuilder {


    static void append() {
        var sb = new StringBuilder();
        sb.append("Hi ! ", 0, 2);//Hi
        sb.append(19);
        sb.append('a');
        sb.append(19.0).append(true);

        System.out.println(sb);//Hi19a19.0true
    }

    static void insert() {
        var sb = new StringBuilder("hello world");
        sb.insert(0, "Hi ! ")
                .insert(sb.indexOf("world"), "weired ");
        sb.insert(sb.length(), " kwik kwik".toCharArray(), 0, 5);
        sb.insert(sb.length(), " kwik kwik", 0, 5);

        //sb.insert(-1  , 88);//StringIndexOutOfBoundsException
        System.out.println(sb);//Hi ! hello weired world kwik kwik
    }

    static void delete() {
        var sb = new StringBuilder("hello world");
        sb.deleteCharAt(0).delete(0, sb.indexOf("world"));

        sb.delete(1, 1);//no action

        System.out.println(sb);//world
    }

    static void replace() {
        var sb = new StringBuilder("hello world");

        sb.replace(0, sb.indexOf("world"), "Bye");
        System.out.println(sb);//Byeworld
    }


    static void lookup() {

        var sb = new StringBuilder("hello world");

        System.out.println(sb.indexOf("world"));
        System.out.println(sb.lastIndexOf("world"));
        //StringBuilder does not have contains method , nor matches
    }

    static void substring() {
        var sb = new StringBuilder("hello world");
        System.out.println(sb.substring(sb.indexOf(" ")));//" world"
        System.out.println(sb.substring(sb.indexOf(" "), sb.length()));//" world"
        System.out.println(sb.subSequence(2, 5));//llo
    }

    static void comparing() {
        /*
         * StringBuilder does not have equalsIgnoreCase , contentEquals or compareToIgnoreCase
         * */
        var sb = new StringBuilder("hello world");
        System.out.println(sb.equals("hello world"));//true

        System.out.println(sb.compareTo(new StringBuilder()));
    }

    static void reverse() {
        System.out.println(new StringBuilder("hello").reverse());
    }

    static void resize() {
        var sb = new StringBuilder("hello world");
        sb.setLength(5);
        System.out.println(sb);//hello
    }

    public static void main(String[] args) {
        //replace();
//        lookup();
//        substring();
//        comparing();
//        transformation();
//        concat();
//        append();
//        insert();
//        delete();
//        replace();
//        reverse();
        resize();

    }


}