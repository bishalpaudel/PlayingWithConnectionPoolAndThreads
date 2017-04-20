import java.sql.SQLException;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public class ServletThread extends Thread {
    @Override
    public void run() {
        try {
            (new Servlet()).doGet();
            System.out.println(System.currentTimeMillis());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
