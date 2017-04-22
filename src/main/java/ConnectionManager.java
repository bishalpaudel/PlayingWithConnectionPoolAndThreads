import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public class ConnectionManager {

    List<Connection> connections = new ArrayList();

    public ConnectionManager() throws SQLException {
    }

    public static Connection getConnection() throws Exception {
        try{
            DBConnectionPool pool = DBConnectionPool.getInstance();
            return pool.getObject();
        }catch (NullPointerException | SQLException ex){
            throw ex;
        }
    }

    public static void closeConnection(Connection connection) throws Exception {
        DBConnectionPool.getInstance().close(connection);
    }
}
