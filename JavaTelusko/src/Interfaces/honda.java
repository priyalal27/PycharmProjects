package Interfaces;

public class honda implements car1,car2 {
    @Override
    public void go() {
        System.out.println("Honda go method"+car1.fuel_type);
    }

    @Override
    public void stop() {
        System.out.println("Honda stop method"+car2.fuel_type);
    }
}
