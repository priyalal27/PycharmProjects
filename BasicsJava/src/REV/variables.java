package REV;

public class variables {


    /*
    * Local
    * Instance
    * Static
    * final

    * Local Variables: Declared inside a method, constructor, or block. Accessible only within that block.
      Instance Variables: Declared inside a class but outside any method. Each object of the class has its own copy.
      Static Variables: Declared with the static keyword inside a class. Shared by all objects of the class.
      Final Variables: Declared with final keyword. Value cannot be changed once assigned.
    * */


    static int y=30;
    int x=10;



    public void local_var(){
        int x=20;
        System.out.println("Local variable "+x);
        System.out.println("Global variable "+this.x);
        System.out.println("Printing static variable "+y);
    }
    /**
    public static void method(){
      java: non-static variable x cannot be referenced from a static context
        System.out.println(x);
    }***/

    public static void main(String[] args) {
        variables var=new variables();
        var.local_var();
    }

}
