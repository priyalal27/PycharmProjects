package REV;

public class encapsulation {

    int roll_number;
    String name;

    public void setRoll_number(int roll_number){
        this.roll_number=roll_number;
                }

    public int getRoll_number(){
        return this.roll_number;
    }



    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }


//Encapsulation binding of data into single unit.
    public void check_general_questions(){
        System.out.println();
    }

}
