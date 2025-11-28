package com.teleusko;

import jakarta.persistence.*;

@Entity
public class Marks {

    @Id
    private int id;
    private String Maths;
    private String Science;
    private String English;
    private String Hindi;
    private String SST;


    @OneToOne
    @MapsId
    @JoinColumn(name="student_id")
    private Student student;


    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaths() {
        return Maths;
    }

    public void setMaths(String maths) {
        Maths = maths;
    }

    public String getScience() {
        return Science;
    }

    public void setScience(String science) {
        Science = science;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getHindi() {
        return Hindi;
    }

    public void setHindi(String hindi) {
        Hindi = hindi;
    }

    public String getSST() {
        return SST;
    }

    public void setSST(String SST) {
        this.SST = SST;
    }

}
