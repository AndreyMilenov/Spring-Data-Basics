package JavaDBAppsIntroductuinExer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    private static final DBTOOLS DB_TOOLS = new DBTOOLS
            ("root", "Andrei:Milenov", "minions_db");
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {

   increaseAgeStoredProcedure();
    

    }

    private static void increaseAgeStoredProcedure() throws SQLException, IOException {
        int minionId = Integer.parseInt(READER.readLine());
        CallableStatement callableStatement = DB_TOOLS.getConnection().prepareCall("CALL minions_db.usp_get_older(?)");
        callableStatement.setInt(1,minionId);
        ResultSet i = callableStatement.executeQuery();
        i.next();
        System.out.println(i.getString(1) + " " + i.getInt(2));

    }

    private static void increaseMinionsAge() throws IOException, SQLException {
        int[] minionIds = Arrays.stream(READER.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.stream(minionIds).forEach(id -> {
            String sql = "UPDATE minions SET name = LOWER(name), age = age + 1 WHERE id IN(?)";
            try {
                PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(sql);
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        ResultSet resultSet = DB_TOOLS.getConnection().createStatement().executeQuery("SELECT name,age FROM minions");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }

    }

    private static void printAllMinionsNames() throws SQLException {
        ResultSet resultSet = DB_TOOLS.getConnection().createStatement().executeQuery("SELECT name FROM minions");
        ArrayDeque<String> minionsNames = new ArrayDeque<>();
        while (resultSet.next()) {
          String name = resultSet.getString("name");
          minionsNames.add(name);
        }
        while (!minionsNames.isEmpty()) {
            System.out.println(minionsNames.removeFirst());
            if (!minionsNames.isEmpty()) {
                System.out.println(minionsNames.removeLast());
            }
        }
    }

    private static void removeVillain() throws IOException, SQLException {
        int villainId = Integer.parseInt(READER.readLine());
        String villainName = findVillainByID(villainId);
        if (villainName.isEmpty()) {
            System.out.println("No such villain is found");
        }else {
            int releasedMinionsCount = releaseMinions(villainId);
            deleteVillain(villainId);
            System.out.printf("%s was deleted%n%d minions released",villainName,releasedMinionsCount);
        }
    }

    private static void deleteVillain(int villainId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.
                getConnection().prepareStatement("DELETE FROM villains WHERE id = ?");
        preparedStatement.setInt(1,villainId);
        preparedStatement.execute();

    }

    private static int releaseMinions(int villainId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("DELETE FROM minions_villains WHERE villain_id = ?");
        preparedStatement.setInt(1,villainId);
        return preparedStatement.executeUpdate();
    }

    private static void changeTownNamesCasing() throws IOException, SQLException {
        String country = READER.readLine();
        PreparedStatement preparedStatement = DB_TOOLS
                .getConnection().prepareStatement("UPDATE towns SET name = UPPER(name) WHERE country = ?");

        preparedStatement.setString(1,country);
        int i = preparedStatement.executeUpdate();
        if (i == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d towns names were affected.%n",i);
            preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT name FROM towns WHERE country = ?");
            preparedStatement.setString(1,country);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> names = new ArrayList<>();
            while (resultSet.next()) {
             names.add(resultSet.getString("name"));
            }
            System.out.printf("[%s]",String.join(", ",names));
        }

    }

    private static void addMinion() throws IOException, SQLException {
        String[] minionsTokens = READER.readLine().split("\\s+");

        String minionsName = minionsTokens[1];
        int minionAge = Integer.parseInt(minionsTokens[2]);
        String townName = minionsTokens[3];

        String villainName = READER.readLine().split("\\s+")[1];

        int townId = findTownIdByName(townName);
        if (townId == 0) {
            createTown(townName);
            System.out.printf("Town %s was added to the database", townName);
        }
        int minionId = createMinion(minionsName, minionAge, townId);
        int villainId = findVillainByName(villainName);
        if (villainId == 0 ) {
            villainId = createVillain(villainName);
            System.out.printf("Villain %s was added to the database.",villainName).append(System.lineSeparator());
        }
        populateMinionsVillains(minionId,villainId);
        System.out.printf("Successfully added %s to be minion of %s",minionsName,villainName);
    }

    private static void populateMinionsVillains(int minionId, int villainId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection()
                .prepareStatement("INSERT INTO minions_villains(minion_id, villain_id) VALUE (?, ?)");
        preparedStatement.setInt(1,minionId);
        preparedStatement.setInt(2,villainId);
        preparedStatement.executeUpdate();

    }

    private static int createVillain(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection()
                .prepareStatement("INSERT INTO villains(name, evilness_factor) VALUE (?,'evil')");
        preparedStatement.setString(1,villainName);
        preparedStatement.executeUpdate();
        preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM villains WHERE name = ? ");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    private static int findVillainByName(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection()
                .prepareStatement("SELECT id FROM villains WHERE name = ?");
        preparedStatement.setString(1,villainName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return 0;
    }
    private static String findVillainByID(int villainID) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection()
                .prepareStatement("SELECT name FROM villains WHERE id = ?");
        preparedStatement.setInt(1,villainID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("name");
        }
        return null;
    }

    private static int createMinion(String minionsName, int minionAge, int townId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection()
                .prepareStatement("INSERT INTO  minions(name,age,town_id) VALUE (?,?,?)");
        preparedStatement.setString(1,minionsName);
        preparedStatement.setInt(2,minionAge);
        preparedStatement.setInt(3,townId);
        preparedStatement.executeUpdate();
        preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM minions WHERE name = ?");
        preparedStatement.setString(1,minionsName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    private static int createTown(String townName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("INSERT INTO towns(name) VALUE (?)");
        preparedStatement.setString(1, townName);
        preparedStatement.executeUpdate();

        preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM towns WHERE name = ?");
        preparedStatement.setString(1, townName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getInt("id");
    }

    private static Integer findTownIdByName(String townName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("SELECT id FROM towns WHERE name = ? ");
        preparedStatement.setString(1, townName);
        //  PreparedStatement preparedStatement1 = preparedStatement;
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return 0;
    }

    private static void GetMinionNames() throws IOException, SQLException {
        int villainId = Integer.parseInt(READER.readLine());
        String sql = "SELECT name FROM villains AS vi WHERE id = ?";
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villainId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            System.out.printf("No villain with ID %d exists in database.", villainId);
            return;
        }

        String villainName = resultSet.getString("name");
        System.out.printf("Villain %s", villainName).append(System.lineSeparator());

        sql = "SELECT m.name,m.age FROM minions AS m " +
                "JOIN minions_villains AS mv ON m.id = mv.minion_id " +
                "WHERE villain_id = ?";
        preparedStatement = DB_TOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villainId);
        resultSet = preparedStatement.executeQuery();

        int index = 0;

        while (resultSet.next()) {
            System.out.printf("%d. %s %d", ++index,
                            resultSet.getString("name"), resultSet.getInt("age"))
                    .append(System.lineSeparator());
        }
    }

    public static void GetVillainsNamesM() throws SQLException {
        String sql = "SELECT v.name, COUNT(mv.minion_id) AS count FROM villains AS v " +
                "JOIN minions_villains mv on v.id = mv.villain_id " +
                "GROUP BY v.name " +
                "HAVING count > 15 " +
                "ORDER BY count DESC ";

        ResultSet resultSet = DB_TOOLS.getConnection().createStatement().executeQuery(sql);

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int count = resultSet.getInt("count");
            System.out.printf("%s %d", name, count)
                    .append(System.lineSeparator());
        }
    }
}
