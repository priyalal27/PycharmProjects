public class constructorChaining {

    constructorChaining(){
        this(10);
        System.out.println("Default constructor");
    }

    constructorChaining(int n){
        System.out.println("Parameterized constructor called with value: " + n);
    }

    public static void main(String[] args) {
        constructorChaining cc = new constructorChaining();

    }


}
