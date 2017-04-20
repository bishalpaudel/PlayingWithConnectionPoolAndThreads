import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public class ConnectionManager {

    /*
    * Why field initialization? Because many constructors may need to initialize this same thing.
    * Cons: If each constructor initialize it differently, then field initialization is worthless.
    */
    List<Connection> connections = new ArrayList();

    public ConnectionManager() throws SQLException {
    }

    public Connection getConnection() throws Exception {
        try{
            return DBConnectionPool.getInstance().getConnection();
        }catch (SQLException ex){
            throw ex;
        }
    }

    public void close(Connection connection){
        DBConnectionPool.getInstance().close(connection);
    }
}
