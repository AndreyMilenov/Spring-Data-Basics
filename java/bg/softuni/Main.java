package bg.softuni;

import ORM.EntityManager;
import ORM.MyConnector;
import entities.Order;
import entities.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        MyConnector.createConnection("root", "Andrei:Milenov", "mini_orm");

        Connection connection = MyConnector.getConnection();

         EntityManager<User> userEntityManager = new EntityManager<>(connection);

        //userEntityManager.doCreate(User.class);

        // User pesho = new User("pesho", 43, LocalDate.now());
        // userEntityManager.doAlter(pesho);
        // userEntityManager.persist(pesho);

         User pesho = userEntityManager.findFirst(User.class);
         userEntityManager.delete(pesho);
        // pesho.setId(1L);
        // userEntityManager.persist(pesho);

        // User user = userEntityManager.findFirst(User.class, "age > 40");

        // System.out.println(user);

        // EntityManager<Order> orderEntityManager = new EntityManager<>(connection);
        //  orderEntityManager.doCreate(Order.class);
        //Order m123b4 = new Order("m123b4", LocalDate.now());
        // orderEntityManager.persist(m123b4);


     }
}