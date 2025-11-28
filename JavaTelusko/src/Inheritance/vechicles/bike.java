package Inheritance.vechicles;

public class bike extends vehicle{

    bike(int a, int b) {
        super(a, b);
        System.out.println("Bike class const.");
    }

    @Override
    public String fuel(){
        super.fuel();
        return "electric";
    }
}
