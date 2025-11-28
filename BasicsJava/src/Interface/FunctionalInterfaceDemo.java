package Interface;

/**
 * Demonstration of different ways to use functional interfaces
 */
public class FunctionalInterfaceDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Functional Interface Implementation Demo ===\n");
        
        // 1. Traditional class implementation
        System.out.println("1. Using traditional class implementation:");
        functionalInterface traditional = new functionalInterfaceImpl();
        traditional.singleAbstractMethod();
        
        // 2. Anonymous class implementation
        System.out.println("\n2. Using anonymous class:");
        functionalInterface anonymous = new functionalInterface() {
            @Override
            public void singleAbstractMethod() {
                System.out.println("Anonymous class implementation of singleAbstractMethod()");
            }
        };
        anonymous.singleAbstractMethod();
        
        // 3. Lambda expression implementation
        System.out.println("\n3. Using lambda expression:");
        functionalInterface lambda = () -> {
            System.out.println("Lambda expression implementation of singleAbstractMethod()");
        };
        lambda.singleAbstractMethod();
        
        // 4. Method reference implementation
        System.out.println("\n4. Using method reference:");
        functionalInterface methodRef = FunctionalInterfaceDemo::methodReferenceImplementation;
        methodRef.singleAbstractMethod();
        
        // 5. Demonstrating functional interface usage in higher-order functions
        System.out.println("\n5. Using functional interface as parameter:");
        executeFunction(() -> System.out.println("Passed as lambda parameter"));
        executeFunction(traditional);
        
        System.out.println("\n=== Demo completed ===");
    }
    
    /**
     * Method to be used with method reference
     */
    public static void methodReferenceImplementation() {
        System.out.println("Method reference implementation of singleAbstractMethod()");
    }
    
    /**
     * Higher-order function that accepts functional interface as parameter
     */
    public static void executeFunction(functionalInterface func) {
        System.out.print("Executing passed function: ");
        func.singleAbstractMethod();
    }
}
