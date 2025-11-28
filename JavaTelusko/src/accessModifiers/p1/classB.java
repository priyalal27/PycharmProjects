package accessModifiers.p1;

public class classB {
    public void display(){
        classA A=new classA();
        System.out.println("Inside class B");
        System.out.println("Public variable accessed"+A.pub);
        System.out.println("protected variable accessed"+A.prot);
        System.out.println("default variable accessed"+A.def);

    }
}
