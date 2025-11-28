package Collections.List;

import java.util.LinkedList;
import java.util.Random;

public class LinkedListClass {

    public static void main(String[] args) {

        LinkedList<Integer> ll=new LinkedList<Integer>();
//        Random rand = new Random();
//        int randomInt = rand.nextInt(100);
        for (int i = 0; i <=10; i++) {
                ll.add(i);
        }

        System.out.println(ll);

        ll.add(4,30);

        System.out.println(ll);

        ll.removeFirstOccurrence(30);

        System.out.println(ll);

    }


}
