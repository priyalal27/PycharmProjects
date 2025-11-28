package staticNonStatic;

public class staticclass {

    static int variable;


    public void nonstaticblock(){
        System.out.println(staticclass.variable);
    }

    static {
        System.out.println("This block gets first executed before main method : M1");
    }

    static {
        System.out.println("This block gets first executed before main method : M2");
        System.out.println("Calling static method from static block....");
        staticclass.m1();
    }

     static void m1(){
         System.out.println("In static Method");
     }

    public static void main(String[] args) {
        System.out.println("Main method");
        staticclass.m1();
        staticclass sc= new staticclass();
        sc.nonstaticblock();
    }

}
