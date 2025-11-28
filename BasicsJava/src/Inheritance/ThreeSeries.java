package Inheritance;

public class ThreeSeries extends BMW {

    int modelYear;
    String modelName;
    boolean isCruiseControl;

    ThreeSeries(int modelYear,String modelName,boolean isCruiseControl){
        super(modelYear,modelName);
        this.isCruiseControl=isCruiseControl;
        System.out.println("Inside ThreeSeries class --> "+modelYear+" "+modelName+" "+isCruiseControl);
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Inside three series start method");
    }

    @Override
    public void stop() {
        super.stop();
        System.out.println("Inside five series start method");
    }
}
