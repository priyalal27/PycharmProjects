package Inheritance.vechicles;

public class car extends vehicle{

    car(int a , int b,int c,int d){
        super(a ,b);
        System.out.println("CAR class const.");

    }

    @Override
    public String fuel(){
        return "CNG";
    }

    //Instance method 'dosomething()' in 'Inheritance.vechicles.car' cannot override
    // static method 'dosomething()' in 'Inheritance.vechicles.vehicle'
//    public  void dosomething(){
//        System.out.println("Do something in parent class");
//    }


}
