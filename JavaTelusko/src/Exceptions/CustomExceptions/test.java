package Exceptions.CustomExceptions;

public class test {


    public void validate_checkedexception(){
         throw new CustomUncheckedException("Handling checked exception at runtime");

    }

    public void validate_uncheckedException(){
       throw new CustomUncheckedException("Handling UNchecked exception at compile time");
    }

    public static void main(String[] args) {

        test t= new test();
        t.validate_checkedexception();
        t.validate_uncheckedException();
    }

}
