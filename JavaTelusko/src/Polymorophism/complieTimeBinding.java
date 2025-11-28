package Polymorophism;

public class complieTimeBinding {

    void add(int a, int b){
        System.out.println(" Method one with 2 int signature "+a+b);
    }


    void add(float a, float b){
        System.out.println(" Method one with 2 float signature "+a+b);
    }

    void add(int a, int b, int c){
        System.out.println(" Method one with 3 int  signature "+a+b+c);
    }


    public static void main(String[] args) {
        complieTimeBinding ctb= new complieTimeBinding();
        ctb.add(3,4,5);
        ctb.add(2.9f,9.8f);
        ctb.add(2,3);

    }

}
