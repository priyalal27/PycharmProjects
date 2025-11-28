package accessModifiers.p2;

import accessModifiers.p1.classA;

public class classD extends classA {

    public void display(){
        System.out.println("Inside class D");
        classA A= new classA();
        System.out.println("Public values :  "+A.pub);
        System.out.println(A.getPriv());

        //to access the protected value from class A, we need to create instance of class c
        classD D =new classD();
        System.out.println("Default values "+D.prot);


    }

}
