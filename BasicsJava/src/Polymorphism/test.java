package Polymorphism;


/*Same method name, same parameters, but in different classes (parent-child relationship).*/
public class test {

    public static void main(String[] args) {
        Animal c =new Cat();
        Animal d =new Dog();


        c.sound();
        d.sound();
    }
}
