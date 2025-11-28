package REV.arraypacakge;

import AccessModifiers.p1.Student;

import java.util.Arrays;

public class arr1 {

    public int[] returning_array(){

        return new int[]{1,2,3,4,3,2,2,3,1,43,2};
    }


    public static void main(String[] args) {

        student[] s=new student[5];

        s[0]=new student(1,"Priya");
        s[1]=new student(2,"Divya");
        s[2]=new student(3,"Hina");
        s[3]=new student(4,"Raj");
        s[4]=new student(5,"Saroj");

        for(student x:s){
            System.out.println("Name "+x.name);
        }

        arr1 aa=new arr1();
        System.out.println(Arrays.toString(aa.returning_array()));


    }


}
