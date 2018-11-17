package AppSec.conn;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class MySQLConnUtils {
  
 public static Connection getMySQLConnection()
         throws ClassNotFoundException, SQLException
 {
     // Note: Change the connection parameters accordingly.
     String hostName = "triton.towson.edu"; //"localhost";
     String dbName =  "kclabo1db"; //"freeguard";
     String userName = "kclabo1db";
     String password = "Cosc*7chp"; //"";
     return getMySQLConnection(hostName, dbName, userName, password);
 }
  
 public static Connection getMySQLConnection(String hostName, String dbName,
         String userName, String password) throws SQLException,
         ClassNotFoundException
 {
    
     Class.forName("com.mysql.jdbc.Driver");
     String connectionURL = "jdbc:mysql://" + hostName + ":3360/" + dbName;
  
     return DriverManager.getConnection(connectionURL, userName, password);
 }
}