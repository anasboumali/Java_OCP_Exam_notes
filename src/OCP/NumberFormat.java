package OCP;


import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

public class NumberFormat {


    public static void main(String... args) {

        System.out.println("currencies : " + Currency.getAvailableCurrencies());// [LUF, ZWN, AZN, PKR, MVR, AFA, RUR...

        java.text.NumberFormat euroFormat = java.text.NumberFormat.getCurrencyInstance();
        System.out.println(euroFormat.format(3938));//3 938,00 €

        java.text.NumberFormat dollarFormat = java.text.NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(dollarFormat.format(3938));//$3,938.00

        java.text.NumberFormat frNumberFormat = java.text.NumberFormat.getNumberInstance();
        System.out.println(frNumberFormat.format(33637364.87));//33 637 364,87

        java.text.NumberFormat usNumberFormat = java.text.NumberFormat.getNumberInstance(Locale.US);
        System.out.println(usNumberFormat.format(33637364.86));//33,637,364.86

        try {
            System.out.println(usNumberFormat.parse("33,637,364.86"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Locale loc = Locale.US;//OK
        Locale loc2 = new Locale("en" , "US");//OK
    }

}


