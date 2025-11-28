package com.logic;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Logical1 {

    @Test
    public void max_number_in_Array(){
        int[] arr={10, 5, 20, 8, 7};
        int max=0;
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        System.out.println(max);
    }

    @Test
    public void second_largest_number_in_array(){
        int[] arr={10, 5, 20, 8, 7};
        for (int i = 0; i <arr.length ; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println(arr[(arr.length)-2]);
    }

    @Test
    public void palindrome(){
        String s= "malayalam";
        StringBuilder strbuild= new StringBuilder();
        char[] rev=s.toCharArray();
        for (int i = rev.length-1; i >=0; i--) {
            strbuild.append(rev[i]);
        }
        System.out.println(strbuild);

        if(strbuild.toString().equals(s)){
            System.out.println("Palindrome");
        }
        else {
            System.out.println("Not a palindrome");
        }
    }

    @Test
    public void anagram(){
        String inp1="Conversation, Voices rant on";
        String inp2="A gentleman, Elegant man!";

        Map<Character,Integer> in1=new HashMap<>();
        Map<Character,Integer> in2=new HashMap<>();



    }

}
