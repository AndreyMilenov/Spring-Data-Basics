package JavaDBAppsIntroductuinExer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Andrei:Milenov");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",properties);

        // Statement Logic -> create query
        String sql = "SELECT v.name, COUNT(mv.minion_id) AS count FROM villains AS v " +
                "JOIN minions_villains mv on v.id = mv.villain_id " +
                "GROUP BY v.name " +
                "HAVING count > 15 " +
                "ORDER BY count DESC ";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
            String name = resultSet.getString("name");
            System.out.printf("%s %d",resultSet.getString("name"),resultSet.getInt("count"))
                    .append(System.lineSeparator());
        };



    }
}
