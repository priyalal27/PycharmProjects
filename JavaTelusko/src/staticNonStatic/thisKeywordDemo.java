package staticNonStatic;

public class thisKeywordDemo {


    // this refers to current object address in memry. it gives out memory location .
    // this cannot be used in non static context.
    // refers to current object members which could be variables or methods .

    int b=10;
    static thisKeywordDemo st= new thisKeywordDemo();


    static {
        st=new thisKeywordDemo();
        System.out.println("Address of obj in static block "+st);
    }

    thisKeywordDemo(){
        System.out.println(this);
        System.out.println(this.b);
    }

    public void nonStaticMethod(int b){
        System.out.println(this.b);
        System.out.println(b);
    }

    public static void main(String[] args) {
        new thisKeywordDemo();
        thisKeywordDemo obj =new thisKeywordDemo();
        obj.nonStaticMethod(30);
        System.out.println("Address of obj in main block "+obj);

    }




}
