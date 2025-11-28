package Collections.Queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class PQueue {

    public static void main(String[] args) {
        Queue<Integer> pq=new PriorityQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            pq.add(i);
        }

        System.out.println(pq);

        System.out.println(pq.peek());
        System.out.println(pq.poll());
        System.out.println(pq.peek());
        System.out.println(pq);



    }
}
