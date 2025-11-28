package org.JavabasedAnnotations;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
@Scope("prototype")
public class Desktop implements Computer {

    public Desktop(){
        System.out.println("Object created for Desktop class");
    }

    @Override
    public void compile(){
        System.out.println("Compiling the code on Desktop");
    }

}
