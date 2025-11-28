package Abstraction;

public class test {


    public static void main(String[] args) {
        fiveSeries fs= new fiveSeries();
        threeSeries ts=new threeSeries();
        fs.accelerate();
        fs.commonfunc();

        ts.accelerate();
        ts.commonfunc();
    }
}
