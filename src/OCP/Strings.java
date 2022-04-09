package OCP;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Strings {


    static void creation() {
        System.out.println(new String("".toCharArray()));
        System.out.println(new String("hello".toCharArray(), 0, 2));//he
        System.out.println(new String("hello".getBytes(), 2, 3));//llo
        System.out.println(new String(new StringBuilder()));//

        System.out.println(String.valueOf(2));
        System.out.println(String.valueOf("hello".toCharArray()));//hello
        System.out.println(String.valueOf(new char[]{'a', 'b', 'c'}, 0, 2));//ab
    }

    static void classic() {
        String str = "helLo World";
        System.out.println(str.isBlank());//false
        System.out.println("  ".isBlank());//true
        System.out.println(" \n\t".isBlank());//true
        System.out.println(str.isEmpty());//false

        //lower upper case
        System.out.println(str.toLowerCase());//hello world
        System.out.println(str.toUpperCase());//HELLO WORLD

        System.out.println(" anas ".trim());//anas

        System.out.println(str.length());//11
        //System.out.println("".charAt(0));//StringIndexOutOfBoundsException
        System.out.println(str.charAt(0));//h
    }

    static void replace() {
        String str = "hello World ";

        /*replace all occurrences of oldChar with new Char*/
        System.out.println(str.replace('l', 'L'));//heLLo WorLd

        /*replace all occurrences*/
        System.out.println(str.concat("hello").replace("hello", "bye"));//bye World bye

        //regex
        System.out.println(str.replaceFirst("\s", "[whitespace]"));//hello[whitespace]World
        System.out.println(str.replaceAll("\s", "[whitespace]"));//hello[whitespace]World[whitespace]
    }


    static void lookup() {

        String str = "hello world hello";
        System.out.println(str.indexOf('z'));//-1
        System.out.println(str.indexOf('l'));//2
        System.out.println(str.indexOf('l', str.indexOf("world")));//9
        System.out.println(str.indexOf("hello", 5));//12

        System.out.println(str.lastIndexOf('l'));//15
        /*lastIndexOf(char , fromIndex) , look for the last occurrence between 0 to fromIndex inclusive)*/

        System.out.println(str.lastIndexOf('l', 2));//2
        System.out.println(str.lastIndexOf("l", 5));//3

        System.out.println(str.contains("world"));//true
        String regex = ".[a-zA-Z]{3}.";
        System.out.println("1abc3".matches(regex));//true
        System.out.println("1ab3".matches(regex));//false
    }

    static void substring() {
        String str = "hello world";
        System.out.println(str.substring(str.indexOf(" ")));//" world"
        System.out.println(str.substring(str.indexOf(" "), str.length()));//" world"
        System.out.println(str.subSequence(2, 5));//llo
    }

    static void comparing() {
        String str = "hello world";
        System.out.println(str.equals("hello world"));//true
        System.out.println(str.equalsIgnoreCase(str.toUpperCase()));//true
        System.out.println(str.contentEquals(new StringBuilder(str)));//true

        System.out.println(str.compareTo("world"));//-15
        System.out.println(str.compareToIgnoreCase(str.toUpperCase()));//0
    }

    static void transformation() {
        String str = "hello world";
        System.out.println(str.toCharArray());//hello world (char[])
        System.out.println(Arrays.toString(str.getBytes()));//[104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100]

        //to char stream (ex : letters frequency)
        System.out.println(str.chars().boxed().collect(Collectors.groupingBy(Character::toString, Collectors.counting())));//{ =1, r=1, d=1, e=1, w=1, h=1, l=3, o=2}

        //to lines stream
        var para = str + "\n" + "bye world";
        para.lines().forEach(System.out::println);

        //strip
        var fuzzy = "\t hello \n";
        System.out.println(fuzzy.stripLeading());//hello \n
        System.out.println(fuzzy.stripTrailing());//\t hello
        System.out.println(fuzzy.strip());//hello

        //repeat
        System.out.println("h".repeat(4));//hhhh
    }

    static void split() {
        String str = "hello weird world";
        System.out.println(Arrays.toString(str.split("\s")));//[hello, weird, world]
        System.out.println(Arrays.toString(str.split("\s", 2)));//[hello, weird world]
    }

    static void formatting() {

        //C like formatting  , can throws IllegalFormatPrecisionException , IllegalFormatConversionException
        System.out.println(String.format("hello Mr %6s , your age is %d", "anas", 10));//hello Mr   anas , your age is 10
        System.out.println(String.format("your balance is %6.4f$", 100.390));//your balance is 100,3900$
        System.out.println(String.format(Locale.US, "your balance is %6.4f$", 100.390));//your balance is 100.3900$
    }

    static void concat() {
        System.out.println("hello ".concat("world"));//hello world
        System.out.println(String.join(" ", List.of("Hi", "I'm", "Anas")));//Hi I'm Anas
        System.out.println(String.join(" ", "Pff !", new StringBuilder("so boring")));//Pff ! so boring
    }

    public static void main(String[] args) {
        creation();
        //classic();
        //replace();
        //lookup();
//        substring();
//        comparing();
//        transformation();
//        split();
//        formatting();
//        creation();
//        concat();
    }


}