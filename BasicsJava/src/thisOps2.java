public class thisOps2 {


    int modelYear;
    String modelName;

    thisOps2(String modelName){
        this(2020,modelName);
    }

    thisOps2(int modelYear,String modelName){
            this.modelName=modelName;
            this.modelYear=modelYear;
    }

    public static void main(String[] args) {
        thisOps2 t1 = new thisOps2("mustang");
        thisOps2 t2 = new thisOps2(2021, "Jaguar");

        System.out.println(t1.modelYear);
        System.out.println(t2.modelYear);

    }
}
