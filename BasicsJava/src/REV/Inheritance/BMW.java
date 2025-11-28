package REV.Inheritance;

public class BMW {

    String make;
    String model;
    int year;

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    BMW(String make, String model, int year){
        this.make=make;
        this.model=model;
        this.year=year;
    }

    public void start(){
        System.out.println("Staring parent class method");
    }

    public void stop(){
        System.out.println("stopping parent class method");
    }



}
