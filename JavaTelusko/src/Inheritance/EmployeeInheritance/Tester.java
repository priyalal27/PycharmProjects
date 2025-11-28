package Inheritance.EmployeeInheritance;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Tester extends Employee{
    String[] tools;
    public Tester(int id, String name, String department, int salary,String[] tools) {
        super(id, name, department, salary);
        this.tools=tools;
    }

    @Override
    protected void work(){
        super.work();
        System.out.println("Tester is working on "+ Arrays.toString(tools));
    }
}
