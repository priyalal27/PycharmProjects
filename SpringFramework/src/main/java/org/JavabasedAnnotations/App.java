package org.JavabasedAnnotations;

import org.JavabasedAnnotations.Alien;
import org.JavabasedAnnotations.Desktop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App 
{
    public static void main( String[] args )
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        System.out.println("\n--- Testing Primary ---");
        Alien obj1=context.getBean(Alien.class);
        obj1.code(); // This should use Laptop because it's marked with @Primary



        //
//        Desktop dt2= context.getBean(Desktop.class);
//        dt2.compile();
//









    }
}
