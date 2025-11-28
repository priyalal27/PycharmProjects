package REV.arraypacakge;


import java.util.Arrays;

public class arraysclass {

    public static void main(String[] args) {
        //Array declaration
        int[] a={1,2,3,4,5,6,7,4,3,2};
        for(int x:a){
            System.out.println(x);
        }

        int[] b=new int[30];
        b= new int[]{1, 2, 3, 4, 5, 6, 7, 4, 3, 2, 4, 2, 2, 3, 2, 2, 32, 323};

        System.out.println(Arrays.binarySearch(b,4));



    }

}
