package Inheritance.vechicles;

public class vehicle {

    int x=20020;

    vehicle(int a , int b){
        System.out.println("Parent int values "+a+"  "+b);
    }

    public String fuel(){
        System.out.println("Parents class is having fuel as PETROL");
        return "Petrol";
    }

    public static void dosomething(){
        System.out.println("Do something in parent class");
    }

}

