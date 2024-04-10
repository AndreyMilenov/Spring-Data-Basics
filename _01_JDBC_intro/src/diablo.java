import java.sql.*;
import java.util.Scanner;

public class diablo {
    public static void main(String[] args) throws SQLException {
        // Connect to DB
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/diablo", "root", "Andrei:Milenov");
        // Execute Query
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("" +
                "SELECT u.id,u.first_name,u.last_name ,COUNT(ug.game_id) " +
                "FROM users AS u " +
                "JOIN user_games AS ug ON ug.user_id = u.id " +
                "WHERE u.user_name = ?; ");
        preparedStatement.setString(1, username);
        ResultSet result = preparedStatement.executeQuery();
        Object userId = result.getObject(1);

        // Print Result
        if (userId != null){
            System.out.printf("User: %s%n" +
                    "%s %s has played %d games",username,
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4));
        } else {
            System.out.println("No such user exists");
        }
    }
}
