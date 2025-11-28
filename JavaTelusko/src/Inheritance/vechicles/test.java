package Inheritance.vechicles;

public class test {

    public static void main(String[] args) {
        bike bi= new bike(10,20);
        bus bu = new bus(40,03);
        car ca = new car(100,200,400,5000);

        System.out.println(bi.fuel());
        System.out.println(bu.fuel());
    }


}
