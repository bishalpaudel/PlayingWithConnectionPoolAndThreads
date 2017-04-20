import java.io.File;
import java.sql.*;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Main main = new Main();

        long startTime = System.currentTimeMillis();
        System.out.println(startTime);

        /* Thread creation by extending Thread class. */
        for(int i = 0; i <= 100; i++){
            Thread thread = new ServletThread();
            thread.start();
        }

        /* Thread creation by implementing Runnable interface. */
        for(int i = 0; i <= 100; i++){
            Thread thread = new Thread(new Runnable(){
                public void run(){
                    try {
                        (new Servlet()).doGet();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread_"+i);
            thread.start();
        }

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

    }

}

