package Inheritance.EmployeeInheritance;

public class Employee {

    int id;
    String name;
    String department;
    int salary;

    public Employee(int id, String name, String department, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

   protected void work(){
       System.out.println("Common work for everyone");
       System.out.println("ID :"+id);
       System.out.println("Name :"+name);
       System.out.println("Department :"+department);
       System.out.println("Salary "+salary);
   }


}
