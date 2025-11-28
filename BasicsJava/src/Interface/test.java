package Interface;

public class test {


    public static void main(String[] args) {

        ThreeSeries ts= new ThreeSeries(2020,"mdk993qadn",true);
        ts.start();

        FiveSeries fs= new FiveSeries(2025,"jdiqwnwod",false);
        fs.sound();
        fs.stop();


        /*Interfaces can now include static methods.
        These methods are called directly using the interface name and
        are not inherited by implementing classes.*/
        BMW.automatic();
    }


}
