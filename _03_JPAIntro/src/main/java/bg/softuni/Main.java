package bg.softuni;


import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("general");

        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Teacher teacher = new Teacher();
        teacher.setName("Gery");

        entityManager.persist(teacher);

        Teacher teacher1 = entityManager.find(Teacher.class, 7);

        System.out.println(teacher1.toString());

        entityManager.getTransaction().commit();

    }
}