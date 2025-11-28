public class objectcounter {
    static int count=0;

    public objectcounter() {
        count=count+1;
    }


    public static void main(String[] args) {
        objectcounter oc1=new objectcounter();
        objectcounter oc2=new objectcounter();
        objectcounter oc3=new objectcounter();
        objectcounter oc4=new objectcounter();

        System.out.println(count);

        second s= new second();
        s.c=200;
        System.out.println(s.c);
    }
}
