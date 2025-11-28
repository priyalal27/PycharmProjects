package Inheritance.constructorChaining;

public class Superclass {

    int x;

    public Superclass() {
        System.out.println("No Argument constructor");
    }

    public Superclass(int x) {
        this();
        this.x = x;
        System.out.println("One Argument Superclass constructor "+x);
    }


}
