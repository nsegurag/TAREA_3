// CONEXXION A MARIADB

package TAREA_3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/Colegio";
    private static final String USER = "root";
    private static final String PASSWORD = "10Chocolates@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

