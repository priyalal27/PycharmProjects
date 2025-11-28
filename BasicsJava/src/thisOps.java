public class thisOps {

    int x=10;

    public thisOps(int x) {
        // this refers to current obj of the class
        // if i want to call one constructor from another , i will use this .
        this.x = x;
    }


    public static void main(String[] args) {
        thisOps t= new thisOps(20);
        System.out.println(t.x);
    }
}
