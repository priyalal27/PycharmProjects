package Inheritance.EmployeeInheritance;

public class Developer extends Employee{
    String technology;

    public Developer(int id, String name, String department, int salary,String technology) {
        super(id, name, department, salary);
        this.technology=technology;
    }

    @Override
    protected void work(){
        super.work();
        System.out.println("Developer is working on "+technology);
    }



}
