package Inheritance;

/**
 * Implicitly every class in java inherit Object class
 * */

public class SingleInheritance {

    public void m1(){
        System.out.println("Method1");
    }

    public static void main(String[] args) {

        SingleInheritance si= new SingleInheritance();
        System.out.println(si.getClass());
        System.out.println(si.hashCode());

    }
}
