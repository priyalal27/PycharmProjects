package Interface;

public class FiveSeries implements BMW{


    int modelYear;
    String modelName;
    boolean isParkingAssisted;

    FiveSeries(int modelYear,String modelName,boolean isParkingAssisted){
        this.isParkingAssisted=isParkingAssisted;
        System.out.println("Inside ThreeSeries class's contructor --> "+modelYear+" "+modelName+" "+isParkingAssisted);
    }

    @Override
    public void start() {
        System.out.println("Inside three series start method");
    }

    @Override
    public void stop() {
        System.out.println("Inside five series stop method");
    }

    @Override
    public void sound(){
        System.out.println("Inside FiveSeries Sounds");
    }


}
