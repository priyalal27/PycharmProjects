package Arrays;

import java.util.Arrays;

public class fiveWithZero {

    public static void main(String[] args) {

        int[] a={3,4,5,6,3,5,6,73,3,2,4,5,3,4,32,2,5,3,5,6,7,3};
        for (int i = 0; i < a.length-1; i++) {
            if(a[i]==5 & a[i+1]==3){
                a[i+1]=0;
            }
        }


        System.out.println(Arrays.toString(a));

        // sum of elemnts in the array:
        int sum =0;
        for (int i = 0; i < a.length; i++) {
            sum =sum +a[i];
        }

        System.out.println("Summation : "+sum);



    }
}
