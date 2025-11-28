package Strings;

public class stringOps1 {

    public static void main(String[] args) {
        // String are immutable : Once String is created , its value cannot be changed .
        // if i want to modify the string, new object is created and the original one remains
        // untouched and unchanged .


        String s1 = "Welcome";
        String s2 = "Welcome";
        String s3 = "welcome";

        System.out.println("s1 identity hash code: " + System.identityHashCode(s1));
        System.out.println("s2 identity hash code: " + System.identityHashCode(s2));
        System.out.println("s3 identity hash code: " + System.identityHashCode(s3));


        // String buffer
        /*The StringBuffer class in Java represents a sequence of characters that can be modified,
        which means we can change the content of the StringBuffer without creating a new object every time.
         It represents a mutable sequence of characters.*/

        StringBuffer sb = new StringBuffer();
        sb.append("Priya ");
        sb.append("Divya ");
        sb.append("Hina");

        System.out.println(sb);
        System.out.println(sb.insert(2,"KAKAK"));
        System.out.println(sb.replace(3,5,"alalla"));
        System.out.println(sb.delete(2,5));
        System.out.println(sb.reverse());



        /*The key features of StringBuffer class are listed below:

        Unlike String, we can modify the content of the StringBuffer without creating a new object.
        All methods of StringBuffer are synchronized, making it safe to use in multithreaded environments.
        Ideal for scenarios with frequent modifications like append, insert, delete, or replace operations.

        Slower in single-threaded programs: It's synchronized,
         meaning it ensures thread safety by allowing only one thread to access it at a time.
          However, in single-threaded environments, this synchronization is unnecessary and
          slows down performance compared to non-synchronized classes like StringBuilder.
            Less efficient than StringBuilder: For non-threaded use cases, StringBuilder is faster and
             has similar functionality. Also, StringBuffer operations like append() or insert() make
              the code longer compared to using simple '+' with String.


        */

    }


}
