package accessModifiers;

import accessModifiers.p1.classA;
import accessModifiers.p1.classB;
import accessModifiers.p2.classC;
import accessModifiers.p2.classD;

public class test {


    public static void main(String[] args) {

        classA A=new classA();
        classB B=new classB();
        classC C=new classC();
        classD D=new classD();

        B.display();
        C.display();
        D.display();

        System.out.println(A.getPriv());

    }

}
