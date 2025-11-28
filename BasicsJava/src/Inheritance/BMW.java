package Inheritance;

public class BMW {

    int modelYear;
    String modelName;

    BMW(int modelYear,String modelName){
            this.modelName=modelName;
            this.modelYear=modelYear;
    }

    public void start(){
        System.out.println("Starting Parent class BMW");

    }

    public void stop(){
        System.out.println("stopping Parent class BMW");

    }




}
