package lab.main;

import lab.composition.Company;
import lab.composition.PlateNumber;
import lab.inheritance.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Testing different Inheritance Type,
        // by changing inheritance type of the inheritance annotation in Vehicle class!
        EntityManagerFactory main =
                Persistence.createEntityManagerFactory("main");

        EntityManager entityManager = main.createEntityManager();

        entityManager.getTransaction().begin();

       // persistAll(entityManager);

        Company company = entityManager.find(Company.class, 2L);
        List<Plane> planes = company.getPlanes();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void persistAll(EntityManager entityManager) {
        PlateNumber plateNumber = new PlateNumber("1234");


        Company company = new Company("AirLine1");
        Vehicle car = new Car("Corsa", BigDecimal.TEN,"Petrol",5,plateNumber);
        Vehicle bike = new Bike("BMX",BigDecimal.ONE,"None");
        Plane plane = new Plane("Boeing",BigDecimal.TEN,"PlaneFuel",100,company);
        Vehicle truck = new Truck("Scania",BigDecimal.ONE,"Diesel",40);


        entityManager.persist(company);
        entityManager.persist(plateNumber);
        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);
        entityManager.persist(truck);
    }
}
