package InnerClasses.nonstatic;

public class outer {

    private static int x=10;
    private int y;

    outer(int y){
        this.y=y;
    }

    void f1(){
        System.out.println("Inside Outer class");
    }

    public class inner{
        void f2(){
            System.out.println("Inside Inner class");
        }

        private void f3(){
            System.out.println(outer.x);
            System.out.println(outer.this.y);

        }

    }


    public static void main(String[] args) {
        outer out = new outer(30);
        out.f1();


        outer.inner inn = out.new inner();
        inn.f2();
        inn.f3();
    }
}
