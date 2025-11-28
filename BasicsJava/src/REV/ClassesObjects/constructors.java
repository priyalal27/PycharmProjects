package REV.ClassesObjects;

public class constructors {

    /**A constructor is a special block of code that is called when an object is created.
     *  Its main job is to initialize the object, to set up its internal state,
     *  or to assign default values to its attributes**/

    constructors(){
        System.out.println("Constructor is called");
    }


    // constructor overloading
    constructors(int a){
        System.out.println("Single arg "+a);
    }

    constructors(int a,int b){
        System.out.println("two arg "+a+" "+b);
    }
    constructors(int a,int b,int c){
        System.out.println("three arg "+a+" "+b+"  "+c);
    }


    public static void main(String[] args) {
        constructors ct=new constructors();
       new constructors(40,50,98);

    }
}
