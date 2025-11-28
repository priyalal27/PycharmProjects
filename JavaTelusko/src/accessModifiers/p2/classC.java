package accessModifiers.p2;

import accessModifiers.p1.classA;

public class classC {

        public void display(){
            classA A=new classA();
            System.out.println("Inside class C");
            System.out.println("Public variable accessed"+A.pub);
//            System.out.println("protected variable accessed"+A.prot);
//            System.out.println("default variable accessed"+A.def);


    }

}
