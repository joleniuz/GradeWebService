package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joel
 */
public class JdbcCon {
    
    private Connection conn;
    private Statement statement;
    
    //Tar in en string för namnet på databasen som ska användas. Ex: "canvasdb", "ladokdb"
    public static Connection openConnection(String database){
        
        Connection conn = null;
        
        if(conn==null){
            
            try {
            String url       = "jdbc:mysql://127.0.0.1:3306/"+database+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
            String user      = "root";
            String password  = "sqladmin";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            
            System.out.println("uppkopplad");
         	
            } catch(SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("inte uppkopplad");
          
            }
            
        } return conn;

        
    }
    
}
