import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Bishal Paudel on 4/19/17.
 */
public abstract class AbstractPool<T> {

    protected ConcurrentLinkedQueue<T> objects = new ConcurrentLinkedQueue();

    private Integer minObject;
    private Integer maxObject;


    public AbstractPool() throws Exception {
        Properties prop = new Properties();
        this.minObject = Integer.parseInt(prop.getProperty("pool.db.minObject"));
        this.maxObject = Integer.parseInt(prop.getProperty("pool.db.maxObject"));

        createMinObjects();
    }

    protected synchronized T getConnection() throws Exception {
        if(! objects.isEmpty())
            return objects.poll();

        T object = createObject();
        objects.add(object);
        return object;
    }

    protected void close(T connection){
        objects.add(connection);
    }

    private void createMinObjects() throws Exception {
        while(objects.size() < minObject){
            objects.add(createObject());
        }
    }

    private void MaintainMaxObjects() {
        while(objects.size() > maxObject){
            objects.poll();
        }
    }

    abstract T createObject() throws Exception;

    public Integer getMinObject() {
        return minObject;
    }

    public void setMinObject(Integer minObject) {
        this.minObject = minObject;
    }

    public Integer getMaxObject() {
        return maxObject;
    }

    public void setMaxObject(Integer maxObject) {
        this.maxObject = maxObject;
    }
}
