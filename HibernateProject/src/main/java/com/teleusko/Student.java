package com.teleusko;

import jakarta.persistence.*;

@Entity
public class Student {


    @Id
    private int rollno;
    private String name;
    private int age;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Marks marks;


    public Marks getMarks(){
        return marks;
    }

    public void setMarks(Marks marks){
        this.marks = marks;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
