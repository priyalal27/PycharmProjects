package Inheritance.EmployeeInheritance;

public class testEmployeeInheritance {


    public static void main(String[] args) {

        String[] projects={"AAP","EA"};
        String[] tools={"JMETER","CURSOR"};

        Developer d= new Developer(33,"Anuja","Engineering",25,"Golang");
        Manager m = new Manager(54,"Tiffany","Engineering",75,projects);
        Tester t= new Tester(82,"Priya","Productivity Engg",32,tools);
        d.work();
        m.work();
        t.work();
    }
}
