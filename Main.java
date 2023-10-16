import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

interface Database {
    //Methods for connexion
    void connect(String url, String user, String password);
    void disconnect();
    //CRUD
    void executeQuery(String query); // Works with select
    void executeUpdate(String query); //Works with insert, update, delete
}

abstract class DataBases implements Database{ //Here you can find the methods that have the same structure
    private Connection connection;

    @Override
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            // Manejo del resultado de la consulta
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
class MariaDB extends DataBases { //Any extra methods added it is because they are different from one another
    private Connection connection;

    public MariaDB(){
        System.out.println("Just a test for mariadb");
    }

    @Override
    public void connect(String url, String user, String password) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class MySQL extends DataBases { //Any extra methods added it is because they are different from one another
    private Connection connection;

    public MySQL(){
        System.out.println("Just a test for mYSQL");
    }
    @Override
    public void connect(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Main{
    public static void main(String[] args) {
        DataBases db = new MariaDB();

    }
}