package REV.Inheritance;

public class FiveSeries extends BMW{


    FiveSeries(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start(){
        System.out.println("Calling child class start");
    }

    @Override
    public void stop(){
        System.out.println("Calling child class stop");
    }



}
