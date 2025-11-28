package REV;

import java.util.StringTokenizer;

public class stringclass {

    public static void main(String[] args) {

        String s="test";
        String s1="test";
        System.out.println(s.hashCode()+"   "+s1.hashCode());

        /**String is an immutable class in Java, which means that once a String object is created,
         *  its value cannot be changed.
         * If you want to modify a string a new String object is created and the original remains unchanged.**/

    // String builder
        /**
         * not thread safe
         * faster
         * mutable
         * **/
        StringBuilder sb= new StringBuilder(s1);
        sb.append("Priya");
        sb.insert(3,"AddingJava");
        sb.deleteCharAt(3);
        sb.reverse();
        System.out.println(sb);
        System.out.println(sb.substring(7,10));

        String converted=sb.toString();
        System.out.println(converted);

        /**
         * StringTokenizer: StringTokenizer class in Java is used to break a string into tokens.
         * **/
        String s2="My name is Priya Lal";
        StringTokenizer st = new StringTokenizer(s2);
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }


    }


}
