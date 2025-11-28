package staticNonStatic;

public class nonstatic {

    int a;

    public void nonstaticmet(){
        System.out.println("Inside non static method");
    }

    {
        System.out.println("Inside non static block");
    }

    nonstatic(){
        System.out.println("Inside constructor");
    }

    static {
        System.out.println("-----------------------------STATIC BLOCK-----------------------------");
    }


    public static void main(String[] args) {
        nonstatic s=new nonstatic();
        System.out.println(s.a);

        new nonstatic();
        new nonstatic();
        new nonstatic();
        new nonstatic();
        nonstatic ss=new nonstatic();
        System.out.println(ss);




    }

}
