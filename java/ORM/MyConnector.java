package ORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector  {
    private static Connection connection;
    private static final String CONNECTIONSTRING = "jdbc:mysql://localhost:3306/";

    public static void createConnection(String username, String password, String dName) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user",username);
        properties.setProperty("password",password);

        connection = DriverManager.getConnection(CONNECTIONSTRING + dName, properties);
    }

    public static Connection getConnection() {
        return connection;
    }
}
