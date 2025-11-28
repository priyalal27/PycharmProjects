package Collections.List;

import java.util.Iterator;
import java.util.Stack;

public class StackClass {
    public static void main(String[] args) {

        Stack<Integer> st=new Stack<Integer>();

        for (int i = 0; i < 20; i++) {
            st.push(i);
        }
        System.out.println("Before pop function() "+st);
        st.pop();

        Iterator<Integer> itr=st.iterator();
        System.out.println("After pop function() ");
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }

}
