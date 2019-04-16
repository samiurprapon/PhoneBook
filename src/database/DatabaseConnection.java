package database;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DatabaseConnection {
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static Connection connection = null;

    private static final String DATABASE_NAME = "jdbc:sqlite:contacts.db";

    private static void databaseConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC class didn't found");
            throw e;
        }

        System.out.println("JDBC Driver has been registered");

        try {
            connection = DriverManager.getConnection(DATABASE_NAME);
        } catch (SQLException e) {
            System.err.println("Connection failed. error details : "+e);
            throw  e;
        }
    }

    public static void executeQuery(String query) {
        Statement statement = null;

        try {
            databaseConnect();
            statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            databaseDisconnect();
        }

    }

    private static void databaseDisconnect(){

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createContact(String name, String phone) {
        String sql = "INSERT INTO peoples(name, phone) VALUES(?, ?)";

        try {
            databaseConnect();

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, phone);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static ResultSet dbExecute(String slqQuery) throws ClassNotFoundException, SQLException {
        CachedRowSetImpl cachedRowSet = null;

        databaseConnect();
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(slqQuery)) {

            cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(rs);
        } catch (SQLException e) {
            System.out.println("Error occurred in dbExecute operation" + e);
        } finally {
            databaseDisconnect();
        }
        return cachedRowSet;
    }
}

