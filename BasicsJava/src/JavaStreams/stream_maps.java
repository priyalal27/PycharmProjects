package JavaStreams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class stream_maps {


    // modify stream filter result
    public static void main(String[] args) {
        Stream.of("Abhijeet","Rama","Alekhya","Don")
                .filter(s->s.length()>3)
                .filter(s->s.endsWith("a"))
                .sorted()
                .map(s->s.toUpperCase())
                .forEach(s-> System.out.println(s));

        // concatinating two streams
        List<String> l1=Arrays.asList("Priya","Divya","Hina");
        List<String> l2=Arrays.asList("EnTC","Mechanical","Mechanical");


        Stream<String> newStream=Stream.concat(l1.stream(),l2.stream())
                .sorted()
                .map(s->s.toUpperCase());


        boolean b=newStream.anyMatch(s->s.equalsIgnoreCase("Hina"));
        System.out.println(b);
    }
}
