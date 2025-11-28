package org.example;

import org.springframework.stereotype.Component;


public class Desktop implements Computer{

    public Desktop(){
        System.out.println("Object created for Desktop class");
    }

    @Override
    public void compile(){
        System.out.println("Compiling the code on Desktop");
    }

}
