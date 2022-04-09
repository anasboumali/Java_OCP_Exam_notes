package OCP;

import java.io.Closeable;

public class Enums {

    enum Birds {
        EAGLE ,
    }

    enum Animal {
        CHICKEN(23) {
            @Override
            int yeaah() {
                return 0;
            }
        }, PENGUIN(75) {
            @Override
            int yeaah() {
                return 0;
            }
        };

        private int numDays;

        private Animal(int numDays) {
            this.numDays = numDays;
        }

        abstract int yeaah();

        public int getNumDays() {
            return numDays;
        }

        public void setNumDays(int numDays) {
            this.numDays = numDays;
        }
    }


    public static void main(String[] args) {
        Animal animal = Animal.CHICKEN;
        System.out.println(animal.numDays);//23



    }
}