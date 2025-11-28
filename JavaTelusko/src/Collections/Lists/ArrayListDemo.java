package Collections.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListDemo {

    public static void main(String[] args) {
        List<Integer> al =new ArrayList<Integer>();
        al.add(30);
        al.add(1,303);

        for (int i = 0; i < 100; i=i+10) {
            al.add(i);
        }

        System.out.println(al);

        al.set(2,93939);

        System.out.println("List after replacement "+al);

        List<Integer> al2= new ArrayList<Integer>();
        for (int i = 10; i < 200; i=i+25) {
            al2.add(i);
        }


        al.addAll(al2);

        System.out.println(al);

        System.out.println(al.size());




        al2.set(3,40404);
        System.out.println("After inserting certain elements at position 3 in al2: "+al2);


        Collections.sort(al2);
        System.out.println("After sorting "+al2);


    }

}
