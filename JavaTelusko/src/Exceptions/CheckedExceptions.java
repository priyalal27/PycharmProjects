package Exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CheckedExceptions {

    public void division() throws ArithmeticException {
        int a = 3 / 0;  // Unchecked exception
    }

    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("emp.txt");
            CheckedExceptions ce = new CheckedExceptions();
            ce.division();
        }
        catch (FileNotFoundException e) {
            System.out.println("❌ File not found error: " + e.getMessage());
        }
        catch (ArithmeticException e) {
            System.out.println("⚠️ Arithmetic error: Cannot divide by zero");
        }
        finally {
            System.out.println("✅ This block always executes");
        }

        System.out.println("Program finished successfully!");
    }
}
