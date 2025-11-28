package AccessModifiers.p1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class defauult {


    public void arraypassing(Student[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.println(" RollNumber-> "+arr[i].rollnumber+" Name-> "+arr[i].name);
        }

    }


    public int[] arr1(){
        return new int[]{1,2,3,4,5,6};
    }

    public static void main(String[] args) {
        Geeks g1= new Geeks();
        System.out.println(g1.b);
        System.out.println(g1.c);


        Student[] arr=new Student[5];

        arr[0]=new Student(22,"Priya");
        arr[1]=new Student(23,"Divya");
        arr[2]=new Student(24,"Hina");
        arr[3]=new Student(25,"Saroj");
        arr[4]=new Student(26,"Rajkumar");

        defauult dd=new defauult();
        dd.arraypassing(arr);


        int[] a=dd.arr1();
        System.out.println(Arrays.toString(a));


    }


}
