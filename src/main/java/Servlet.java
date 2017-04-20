import java.sql.*;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public class Servlet {

    private Connection connection;

    public Servlet() throws SQLException {
        try {
            //Creating connection whenever necessary. Can this be improved?DONE
            connection = DriverManager.getConnection(DataSource.getURL());
        }catch (SQLException exception){
            throw exception;
        }
    }

    public void doGet() throws SQLException {
        try{
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Product");
            while(resultSet.next()){
                String s = resultSet.getString(1);
            }
        }catch (SQLException exception){
            System.out.println("exception occured");
            throw exception;
        }finally {
            //Closing connection for every time connection is opened. What if error occurs?Put it into finally.DONE
            if(connection !=  null)
                connection.close();
        }


        //do query another 100 times.

    }
}
