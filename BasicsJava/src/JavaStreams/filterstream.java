package JavaStreams;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class filterstream {


    public static void main(String[] args) {

        List<String> names=new LinkedList<String>();
        names.add("Priya");
        names.add("Divya");
        names.add("Hina");
        names.add("Lal");
        names.add("JAVA");
        names.add("Priya");

        names.stream()
                .filter(n->n.startsWith("P"))
                .forEach(System.out::println);
        long c=names.stream().filter(n->n.startsWith("P")).count();
        System.out.println(c);

        long d=Stream.of("Java","Python","Javascripts","React","Django")
                .filter(s->{
                    s.startsWith("J");
                    return true;
                }).count();



        names.stream()
                .filter(s->s.startsWith("J"))
                .filter(s->s.length()>3)
                .forEach(s-> System.out.println(s.toLowerCase()));


    }
}
