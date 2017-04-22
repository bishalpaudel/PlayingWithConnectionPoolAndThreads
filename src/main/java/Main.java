import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Main main = new Main();

        long startTime = System.currentTimeMillis();
        System.out.println(startTime);

        List<Thread> threads = new ArrayList();
        /* Thread creation by extending Thread class. */
        for(int i = 0; i <= 1000; i++){
            Thread t = new Thread(new ServletThread());
            threads.add(t);
        }

        for(Thread t: threads){
            t.start();
        }

        for(Thread t: threads) {
            try {
                t.join();

            } catch (InterruptedException e) {
                System.out.println("interrupted from main");
            }
        }

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

    }

}

