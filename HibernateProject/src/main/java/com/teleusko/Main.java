package com.teleusko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    private SessionFactory sessionFactory;

    public void initConfiguration() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Marks.class)
                .configure()
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.initConfiguration();

        try (Session session = app.sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Student student = new Student();
            student.setRollno(100);
            student.setName("Priya");
            student.setAge(25);

            Marks marks = new Marks();
            marks.setMaths("95");
            marks.setScience("93");
            marks.setEnglish("90");
            marks.setHindi("88");
            marks.setSST("91");

            // ✅ Set both sides of relationship
            marks.setStudent(student);
            student.setMarks(marks);

            // ✅ Persist only Student (cascade saves Marks automatically)
            session.persist(student);

            tx.commit();
            System.out.println("✅ Student and Marks saved successfully!");
        }

        try (Session session = app.sessionFactory.openSession()) {
            Student s = session.find(Student.class, 100);
            System.out.println("📘 Fetched from DB: " + s);
        }

        app.sessionFactory.close();
    }
}
