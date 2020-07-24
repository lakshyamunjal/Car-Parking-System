// Singleton class
package finalProject;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection conn = null; 
    private static ConfigurationInfo config = null;
    private static String driver, url, username, password;
    
    private DatabaseConnection() {

    }
    
    public static Connection newInstance(){
        synchronized(DatabaseConnection.class)
        {
            if(conn == null)
            {
                synchronized(DatabaseConnection.class)
                {
                    config = ConfigurationInfo.getDatabaseConfiguration();
                    try{
                        driver = config.getDriver();
                        url = config.getUrl();
                        username = config.getUsername();
                        password = config.getPassword();
                        
                        Class.forName(driver);
                        conn = DriverManager.getConnection(url, username, password);
                    }
                    catch(Exception  e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }   
        return conn;
    }
}
