package Inheritance.constructorChaining;

public class ChildClass extends Superclass{

    public ChildClass() {
        System.out.println("No Arg child class constructor");
    }

    public ChildClass(int x) {
        super(10);
        System.out.println("Argumented child class constructor");
    }


    public static void main(String[] args) {
        ChildClass cc = new ChildClass(30);
    }


}
