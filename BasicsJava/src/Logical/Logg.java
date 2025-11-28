package Logical;

import java.util.Arrays;
import java.util.HashMap;

public class Logg {


    public void max_in_array(int[] arr){
        int max=0;
        for (int i = 0; i < arr.length; i++) {
            if(max<arr[i]){
                max=arr[i];
            }
        }
        System.out.println(max);
    }


    public void second_largest(int[] arr){
        System.out.println("Sorting --------");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;

                }

            }
        }
        System.out.println("Printing second largest "+arr[arr.length-2]);
    }


    public void palindrome(String text) {
        StringBuffer sb = new StringBuffer(text);
        String reversedSt = sb.reverse().toString();

        System.out.println("Reversed String: " + reversedSt);

        if (text.equals(reversedSt)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not a palindrome");
        }
    }



    /*do it after hashmap*/
    public void anagram(String s1,String s2){
             s1=s1.replace(" ","").toLowerCase();
             s2=s1.replace(" ","").toLowerCase();

             if(s1.length()!=s2.length()){
                 System.out.println("Not an Anagram");
             }

        HashMap<Character,Integer> hm1=new HashMap<Character,Integer>();
        HashMap<Character,Integer> hm2=new HashMap<Character,Integer>();

        for (char c1:s1.toCharArray()){
            hm1.put(c1,hm1.get(c1)+1);
        }

    }




    public static void main(String[] args) {
    Logg l =new Logg();
    int[] a={10, 5, 20, 8, 7};
    l.max_in_array(a);
    l.second_largest(a);
    l.palindrome("hello");

}
}
