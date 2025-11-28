package Interface;

public class ThreeSeries implements BMW,car {

    int modelYear;
    String modelName;
    boolean isCruiseControl;

    ThreeSeries(int modelYear,String modelName,boolean isCruiseControl){
        this.isCruiseControl=isCruiseControl;
        System.out.println("Inside ThreeSeries class --> "+modelYear+" "+modelName+" "+isCruiseControl);
    }

    @Override
    public void start() {
        System.out.println("Inside three series start method");
    }

    @Override
    public void stop() {
        System.out.println("Inside three series stop method");
    }

    @Override
    public void sound(){
        System.out.println("Inside ThreeSeries Sounds");
    }
}
