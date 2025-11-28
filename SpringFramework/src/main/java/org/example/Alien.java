package org.example;

public class Alien {

    public int x;
    private Computer com;



    public Alien(int x, Computer com, String salary) {
        this.x = x;
        this.com = com;
        this.salary = salary;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Computer getCom() {
        return com;
    }

    public void setCom(Computer com) {
        this.com = com;
    }

    private String salary;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void code(){

        System.out.println("Coding");
        com.compile();
    }

    public Alien(){
        System.out.println("Obejct created for Alien claSS");
    }
}
