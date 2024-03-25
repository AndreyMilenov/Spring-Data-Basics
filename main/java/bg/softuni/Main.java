package bg.softuni;

import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        // Creating entity in the database.

//        Student student = new Student();
//        student.setName("pesho");
//        session.save(student);

        // Getting the entity whi first create from the database.

//        Student student = session.get(Student.class, 1);
//        System.out.printf("%d - %s",student.getId(),student.getName());
//
//        // Changing the name of the entity in the database.
//        student.setName("Updated");

        // Getting all data from table and filled in List.datatype.

        List<Student> studentList = session.createQuery("FROM Student", Student.class).list();

        for (Student current : studentList) {
            System.out.println(current);
        }

        session.getTransaction().commit();
        session.close();


    }
}