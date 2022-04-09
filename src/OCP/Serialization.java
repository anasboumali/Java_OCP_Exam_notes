package OCP;

import java.io.*;

public class Serialization {


    static void persist() {
        /*
         * Here the transient & static variables are not persisted
         * If the nested object House is null , the code does not throws an exception , and persist null
         * The nested objects should implement Serializable , otherwise , a NotSerializableException will be thrown
         * InvalidClassException If mismatch between versions
         * SerialPersistentFields take precedence over transient
         * Only fields in serialPersistentFields will be persisted
         * When using putFields with serialPersistentFields ,fields must be declared in serialPersistentFields
         * During deserialization , the new fields will be initialized to their Java (any prior initialization will be discarded)
         * */
        class House implements Serializable {
            String address;

            public House(String address) {
                this.address = address;
            }

            @Override
            public String toString() {
                return "House{" +
                        "address='" + address + '\'' +
                        '}';
            }
        }
        class Person implements Serializable {

            static final long serialVersionUID = 1;

            private final static ObjectStreamField[] serialPersistentFields = {
                    new ObjectStreamField("age", int.class),
                    new ObjectStreamField("name", String.class),
            };

            transient int age;
            String name;
            House house;
            static String country = "france";

            public Person(int age, String name, House house) {
                this.age = age;
                this.name = name;
                this.house = house;
            }

            private void writeObject(ObjectOutputStream oos) throws IOException {
//                ObjectOutputStream.PutField putFields = oos.putFields();
//                putFields.put("name", name);
//                putFields.put("age", age);
//                oos.writeFields();

                oos.defaultWriteObject();
                oos.writeObject(house);
            }

            private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
//                ObjectInputStream.GetField getFields = ois.readFields();
//                this.name = (String) getFields.get("name", null);
//                this.age = getFields.get("age", 0);

                ois.defaultReadObject();
                this.house = (House) ois.readObject();
            }

            @Override
            public String toString() {
                return "Person{" +
                        "age=" + age +
                        ", name='" + name + '\'' +
                        ", country='" + country + '\'' +
                        ", house='" + house + '\'' +
                        '}';
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"))) {
            Person p = new Person(12, "hamza", new House("Bordeaux"));
            p.country = "morocco";
            oos.writeObject(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ser.bin"))) {
            Person p = (Person) ois.readObject();
            System.out.println(p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        persist();
    }
}