package OCP.collections;


import java.util.*;
import java.util.function.BiFunction;

public class Maps {

    /*
        Map
        Implementations : HashMap , LinkedHashMap , TreeMap
        The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls
        As a general rule, the default load factor (.75) offers a good tradeoff between time and space costs.
     */

    static void creation() {
        Map<String, Integer> hashMap = new HashMap<>();
        /*
         * Important about Map.of(...)
         * IllegalArgumentException – if the keys are duplicates
         * NullPointerException – if any key or value is null
         * */
        Map<String, Integer> hashMapFromAnotherMap = new HashMap<>(Map.of("anas", 5));


        Map<String, Integer> hashMapFromBuilder = Map.of("anas", 4, "hamza", 5);//Immutable
        Map<String, Integer> hashMapFromEntries = Map.ofEntries(Map.entry("anas", 3));
        Map<String, Integer> hashMapCopyOf = Map.copyOf(hashMap);//Immutable


        /*
         * LinkedHashMap maintains insertion order
         *
         * */

        Map<String, Integer> EMPTY_MAP = Collections.EMPTY_MAP;
        Map<String, Integer> emptyMap = Collections.emptyMap();
        Map<String, Integer> synchronizedMap = Collections.synchronizedMap(hashMap);
    }

    static void populating() {
        Map<String, Integer> hashMap = new HashMap<>();

        //put
        System.out.println(hashMap.put("anas", 5));//null
        System.out.println(hashMap.put("anas", 8));//5 , replace old value
        System.out.println(hashMap);//{anas=8}

        //putAll
        hashMap.putAll(Map.ofEntries(Map.entry("hamza", 5),
                Map.entry("anas", 10)
//                , Map.entry("hamid", null) //NullPointerException
//                , Map.entry(null, 0) //NullPointerException
        ));

        hashMap.put("hamid", null);

        System.out.println(hashMap);//{anas=10, hamid=null, hamza=5}

        //putIfAbsent
        System.out.println(hashMap.putIfAbsent("anas", 19));//10
        System.out.println(hashMap.putIfAbsent("karim", 19));//null
        System.out.println(hashMap.putIfAbsent("hamid", 20));//null
        System.out.println(hashMap);//{karim=19, anas=10, hamid=20, hamza=5}
    }


    static void removing() {
        Map<String, Integer> hashMap = new HashMap<>(Map.of("anas", 2, "hamza", 6));

        System.out.println(hashMap.remove("hamza"));//6 , return the value
        System.out.println(hashMap.remove(null));//null
        System.out.println(hashMap.remove("anas", 10));//false , because the mapping is with 2 not 10
        System.out.println(hashMap.remove("hamid", 10));//false , there is no "hamid" key
        System.out.println(hashMap.remove("anas", 2));//true
    }

    static void retrieving() {
        Map<String, Integer> hashMap = Map.of("anas", 2, "hamza", 6);

        System.out.println(hashMap.get("anas"));//2
        System.out.println(hashMap.get("unknown"));//null
        System.out.println(hashMap.getOrDefault("unknown", 5));//5

        System.out.println(hashMap.containsKey("anas"));//true
        System.out.println(hashMap.containsValue(2));//true

        //for(var entry : hashMap) { }//Compiler error
        for (var entry : hashMap.entrySet()) {
            System.out.println(entry.getKey() + " , " + entry.getValue());
            //if(entry.getKey().equals("anas"))
            //entry.setValue(10);//UnsupportedOperationException
        }

        hashMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " , " + entry.getValue()));
        hashMap.keySet()/*Set*/.forEach(System.out::println);//hamza , anas
        hashMap.values()/*Collection*/.forEach(System.out::println);//2 6
    }


    static void replacing() {
        Map<String, Integer> hashMap = new HashMap<>(Map.of("anas", 2, "hamza", 6));
        hashMap.put("karim", null);

        //replace
        System.out.println(hashMap.replace(null, null));//null
        System.out.println(hashMap.replace("anas", 10));//2
        System.out.println(hashMap.replace("hamid", 9));//null , key not found
        System.out.println(hashMap.replace("karim", 9));//null , previous value

        System.out.println(hashMap);//{hamza=6, karim=9, anas=10}

        //replace ALl
        hashMap.put("karim", null);

        System.out.println(hashMap.replace("anas", 2, 10));//false , 2 does not match old valye
        System.out.println(hashMap.replace("anas", 10, 20));//true
        System.out.println(hashMap.replace("karim", null, 20));//true
        System.out.println(hashMap);//{hamza=6, karim=20, anas=20
    }

    static void compute() {
        Map<String, Integer> hashMap = new HashMap<>(Map.of("anas", 2, "hamza", 6));
        hashMap.put("hakim", null);
        /*compute
         *if the key exist , compute the new mapping , return the new value
         *if the new mapping return null , remove the mapping
         * if the key does not exist , do nothing , return null
         * if the remapping function throws an unchecked exception , the exception is rethrown
         * */

        //mapping does not exist
        System.out.println(hashMap.compute(null, (s, integer) -> integer));//null , no mapping
        System.out.println(hashMap.compute("achraf", (s, integer) -> integer));//null , no mapping
        //System.out.println(hashMap.compute("achraf", (s, integer) -> s.length() + integer));//NullPointerException , use computeIfPresent()

        //mapping exist
        System.out.println(hashMap.compute("anas", (s, integer) -> integer + s.length()));//2 + 4 = 6
        System.out.println(hashMap.compute("karim", (s, integer) -> s.length()));//5
        System.out.println(hashMap.compute("hakim", (s, integer) -> s.length()));//5

        //mapping removed
        System.out.println(hashMap.compute("hamza", (s, integer) -> null));//null , removed


        System.out.println(hashMap);//{karim=5, hakim=5, anas=4}


        /*compute if absent
         *if the key does not exists or associated to null , map to the new value , return the new value
         *if the key exists and associated to a non-null value , do nothing , return old value
         * */
        hashMap.put("hakim", null);
        //key already exists, do nothing , return old value
        System.out.println(hashMap.computeIfAbsent("anas", s -> s.length() * 2));//6

        //keys does not already exist , or exists but associated with null , map to the new value , return new value

        System.out.println(hashMap.computeIfAbsent(null, s -> 10));//10
        System.out.println(hashMap.computeIfAbsent("hamza", s -> s.length() * 2));//10
        System.out.println(hashMap.computeIfAbsent("hakim", s -> s.length() * 2));//10

        //System.out.println(hashMap.computeIfAbsent("achraf", null));//NullPointerException
        System.out.println(hashMap.computeIfAbsent("achraf", s -> null));//null , no mapping

        System.out.println(hashMap);//{null=10, karim=5, hakim=10, anas=6, hamza=10}

        /*Compute if present
         *if the key is not present(non existent key or null value) , do nothing , return null
         *if the key is present , remap and return new value , remove the mapping if the new value is null
         *
         * The key difference between compute & computeIfPresent is the fact that computeIfPresent does not
         * call the mapping function if the key is not present
         * */

        //not present (key does not exist , or mapping with null value)
        hashMap.put("hamza", null);

        System.out.println(hashMap.computeIfPresent("mido", (s, integer) -> s.length() + 10));//null , do nothing
        System.out.println(hashMap.computeIfPresent("hamza", (s, integer) -> 15));//null , do nothing

        System.out.println(hashMap);//{null=10, karim=5, hakim=10, anas=6, hamza=null}

        //present (key exist with non null value)
        System.out.println(hashMap.computeIfPresent("anas", (s, integer) -> integer + s.length()));//10
        System.out.println(hashMap.computeIfPresent("hakim", (s, integer) -> null));//null , removed

        System.out.println(hashMap);//{null=10, karim=5, anas=10, hamza=null}
    }

    static void merge() {
        Map<String, Integer> hashMap = new HashMap<>(Map.of("anas", 2, "hamza", 6));

        /*
         * merge
         *
         * */

        //non existent key , take the value, return the new value
        System.out.println(hashMap.merge("hamid", 2, (integer, integer2) -> integer + integer2));//2
        System.out.println(hashMap);//{hamid=2, hamza=6, anas=2}

        //existent key , non null value , call the mapping , return the new value
        System.out.println(hashMap.merge("anas", 3, (integer, integer2) -> integer + integer2));//5
        System.out.println(hashMap);//{hamid=2, hamza=6, anas=5}

        //existent key , null value , take the value , return the new value
        hashMap.put("anas", null);
        System.out.println(hashMap.merge("anas", 3, (integer, integer2) -> integer + integer2));//3
        System.out.println(hashMap);//{hamid=2, hamza=6, anas=3}
    }

    public static void main(String[] args) {
        //creation();
        //populating();
//        removing();
        //retrieving();
//        replacing();
        //compute();
        merge();


    }
}