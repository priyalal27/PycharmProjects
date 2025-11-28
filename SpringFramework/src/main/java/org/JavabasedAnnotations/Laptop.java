package org.JavabasedAnnotations;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {

    public Laptop(){
        System.out.println("Obejct created for Laptop claSS");
    }

    @Override
    public void compile(){
        System.out.println("Compiling the code on Laptop");
    }

}
