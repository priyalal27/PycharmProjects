package Abstraction;

public class FiveSeries extends BMW {


    int modelYear;
    String modelName;
    boolean isParkingAssisted;

    FiveSeries(int modelYear,String modelName,boolean isParkingAssisted){
        super(modelYear,modelName);
        this.isParkingAssisted=isParkingAssisted;
        System.out.println("Inside ThreeSeries class --> "+modelYear+" "+modelName+" "+isParkingAssisted);
    }

    @Override
    public void start() {
        System.out.println("Inside three series start method");
    }

    @Override
    public void stop() {
        System.out.println("Inside five series stop method");
    }

}
