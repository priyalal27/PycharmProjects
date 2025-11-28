package staticNonStatic;

public class demo {

    static demo d;

    static {
        System.out.println(d);
        d= new demo();

    }

    public static void main(String[] args) {
        System.out.println(demo.d);
    }

}
