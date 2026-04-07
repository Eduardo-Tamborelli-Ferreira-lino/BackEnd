package Maven.Util;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3346/CyberPunk_Garage?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "231231";

    public static java.sql.Connection conectar()throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
