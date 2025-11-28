package REV.Inheritance;

public class ThreeSeries extends BMW{

    boolean cruisecontrol;

    ThreeSeries(String make, String model, int year,boolean cruisecontrol) {
        super(make, model, year);
        this.cruisecontrol=cruisecontrol;
    }

    @Override
    public void start(){
        System.out.println("Calling child class start");
    }

    @Override
    public void stop(){
        System.out.println("Calling child class stop");
    }

    public void displayvalues(){
        System.out.println("Printing threeseries values"+getMake()+"  "+cruisecontrol+"  "+getModel()+"  "+getYear());
    }
}
