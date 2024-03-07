import java.sql.*;
import java.util.Properties;


public class Main {
    public static void main(String[] args) throws SQLException {


        Properties credentials = new Properties();
        credentials.setProperty("user", "root");
        credentials.setProperty("password", "Andrei:Milenov");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", credentials);

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM `employees` LIMIT 10");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            long id = resultSet.getLong("employee_id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            double salary = resultSet.getDouble("salary");
            Timestamp hireDate = resultSet.getTimestamp("hire_date");

            System.out.printf("%d - %s %.2f %s%n",id,first_name,salary,hireDate);

        }

    }
}
