package org.example;

public class Laptop implements Computer{

    public Laptop(){
        System.out.println("Obejct created for Laptop claSS");
    }

    @Override
    public void compile(){
        System.out.println("Compiling the code on Laptop");
    }

}
