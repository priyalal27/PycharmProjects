package accessModifiers;

import java.util.Scanner;
import static java.lang.Integer.*;

public class SumOfTwoNumbers
{

    public static void main(String[] args) {
        System.out.println("Enter two integers");

        Scanner s= new Scanner(System.in);
        // here instead of Integer.parseInt(s.next()), i have used parseInt(s.next()) as
        // this has been imported on line 4 via static import .
        int num1=parseInt(s.next());
        int num2=parseInt(s.next());
        int result= num1+num2;
        System.out.println("Sum is "+result);
    }
}
