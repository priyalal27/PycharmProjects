package Abstraction;

abstract class BMW {

    int modelYear;
    String modelName;

    BMW(int modelYear,String modelName){
            this.modelName=modelName;
            this.modelYear=modelYear;
    }


    abstract void start();

    public void stop(){
        System.out.println("stopping Parent class BMW");

    }




}
