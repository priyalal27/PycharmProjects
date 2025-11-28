package REV.accessModifiers.package1;

public class class1 {

    private int x=10;
    public int y=20;
    int z=30;
    protected int a=100;


    public void method1(){
        System.out.println("Private access modifier is only accessible to the class "+this.x);
        System.out.println("Public access modifier is accessible everywhere "+this.y);

    }


    /**
     * Types of Access Modifiers
     * There are 4 types of access modifiers available in Java:

     * Default : No keyword required and accessible within the same package.
     * Private : Only accessible within the class.
     * Protected : Accessible within the class and its subclasses.
     * Public : Accessible from anywhere.
     * **/

}
