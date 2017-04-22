import java.sql.SQLException;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public class ServletThread implements Runnable {
    public void run() {
        try {
            (new Servlet()).doGet();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
