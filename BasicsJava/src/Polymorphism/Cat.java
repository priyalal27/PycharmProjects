package Polymorphism;
/*Same method name, but different parameter lists, in the same class.*/
public class Cat extends Animal{

    @Override
    public void sound(){
        super.sound();
        System.out.println("Cat Sounds meow");
    }


    public void sound(String breed){
        System.out.println("Cat sounds ---> "+breed);
    }

}

