package Collections.Set;

import java.util.*;

public class Set1 {
        /*Not ordered , no duplicates*/
    public static void main(String[] args) {

        /*HashSet class implements a hash table and stores elements based on their hash codes.
         It does not guarantee insertion order and allows one null element.*/
        Set<Integer> s= new LinkedHashSet<Integer>();
        s.add(1);
        s.add(43);
        s.add(22);
        s.add(41);
        s.add(1);
        s.add(44);
        s.add(244);
        s.add(42);
        s.add(23);
        s.add(411);

        Iterator<Integer> itr = s.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        Set<Integer> lhs=new HashSet<Integer>();
        lhs.addAll(s);
        System.out.println("Printing Hashset "+lhs);


        //sorted tree set :

        SortedSet<String> ss= new TreeSet<String>();
        ss.add("My");
        ss.add("Name");
        ss.add("is");
        ss.add("Priya");

        System.out.println(ss);

        System.out.println(ss.last());
        System.out.println(ss.reversed());



    }



}
