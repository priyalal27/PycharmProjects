package Inheritance.vechicles;

public class bus extends vehicle{

    bus(int a, int b) {
        super(a, b);
        System.out.println("BUS class const.");
    }

    @Override
    public String fuel(){
        System.out.println("Getting x value from fuel class"+super.x);
        return "Diesel";
    }

}
