package REV.ClassesObjects;

public class StudentClass {

    int rollnumber;
    String name;

    StudentClass(int rollnumber,String name){
        this.rollnumber=rollnumber;
        this.name=name;
    }

    public static void main(String[] args) {
        StudentClass sc= new StudentClass(40,"AliceInWonderLand");
        System.out.println(sc.name);
    }

        /**
         * When an object of a class is created, the class is said to be instantiated.
         * All the instances share the attributes and the behavior of the class.
         * But the values of those attributes, i.e. the state are unique for each object.
         * A single class may have any number of instances.**/
}


/**
Context	                        Default	    Private	    Protected	Public
Same Class	                    Yes	        Yes	        Yes	        Yes
Same Package Subclass	        Yes	        No	        Yes	        Yes
Same Package Non-Subclass	    Yes	        No	        Yes	        Yes
Different Package Subclass	    No	        No	        Yes	        Yes
Different Package Non-Subclass	No	        No	        No	        Yes
 **/


/**
 * When to Use Each Access Modifier in Real-World Projects
 * Private: This is used for encapsulating sensitive data and internal helper methods that should not be accessed outside the class.
 * Example: Private fields in a model class with getter and setter methods.
 * Default (Package-Private): This is suitable for classes and methods that should only be accessible within the same package, often used in package-scoped utilities or helper classes.
 * Protected: This is ideal for methods and fields that should be accessible within the same package and subclasses, commonly used in inheritance-based designs like framework extensions.
 * Public: This is used for classes, methods, or fields meant to be accessible from anywhere, such as API endpoints, service classes, or utility methods shared across different parts of an application.**/