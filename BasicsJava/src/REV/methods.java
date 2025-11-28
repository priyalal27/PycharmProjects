package REV;

public class methods {

    static int x=10;
    int y=20;

    /*
    * Instance method : Access the instance data using the object name. Declared inside a class.
    * Static method : Belongs to class , access static data . We do not need to create object of the class. static method will not have non static values
    *abstarct method : need not give implementation to the method . when extended to sub class , we have to give implemntation to the .
    * */
     public static void method1()
     {
         System.out.println("Calling Method1 of static method "+x);
     }


    public void method2()
    {
        System.out.println("Calling instance Method1 of static method");
    }


    public static void main(String[] args) {
        methods m =new methods();
        m.method2();
        method1();
    }

}
/**
 * this is the output of the code :
 *Calling instance Method1 of static method
 * Calling Method1 of static method 10
 *
 * Here first static method is called .
 * Then class object is creatd and then instance method is called .
 */
