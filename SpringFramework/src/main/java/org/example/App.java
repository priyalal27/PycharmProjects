package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App 
{




    public static void main( String[] args )
    {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        Alien obj1=(Alien)context.getBean("alien");
//        obj1.code();
//        obj1.x=30;
//        System.out.println("Printing Object value which is 30**"+obj1.x);

//        Alien obj2=(Alien)context.getBean("alien");
//        obj2.code();
//        System.out.println(obj2.x);

        // Setter Injection : Added a private variable in Alien class - salary . added getter and setter
        // added salary value in property tag of spring.xml
        // Ref Injection : Added a Laptop reference in Alien class , this object reference is added in
        // spring.xml file with property tag , and intead of value we will use ref .

        Alien obj3=context.getBean("alien",Alien.class);
        System.out.println(obj3.getSalary());



        Computer obj4=context.getBean(Computer.class);
//        Desktop obj5= context.getBean(Computer.class);





    }
}
