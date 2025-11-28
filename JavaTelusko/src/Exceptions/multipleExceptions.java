package Exceptions;

import java.util.Scanner;

public class multipleExceptions {
    public static void main(String[] args) {
       try
       {
        int[] numbers = {10, 20, 30};
        int result = numbers[3] / 0;

       }
       catch (ArrayIndexOutOfBoundsException e) {
           System.out.println("Array index is out of bounds!");
       }
       catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }




    }
}
