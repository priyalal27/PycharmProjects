public class encapsulationStudent {

    private String name;
    private int rollNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber){
        this.rollNumber=rollNumber;
    }


    public static void main(String[] args) {
        encapsulationStudent es=new encapsulationStudent();
        es.setName("Priya");
        es.setRollNumber(36);
        System.out.println(es.getName());
        System.out.println(es.getRollNumber());

    }
}
