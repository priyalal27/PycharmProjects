package Inheritance.EmployeeInheritance;

import java.util.Arrays;

public class Manager extends Employee{

    String[] projects;

    public Manager(int id, String name, String department, int salary,String[] projects) {
        super(id, name, department, salary);
        this.projects=projects;
    }

    @Override
    protected void work(){
        super.work();
        System.out.println("Manager is working on "+ Arrays.toString(projects));
    }
}
