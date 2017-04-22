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
        this.minObject = 1;//Integer.parseInt(prop.getProperty("pool.db.minObject"));
        this.maxObject = 1;//Integer.parseInt(prop.getProperty("pool.db.maxObject"));

        createMinObjects();
    }

    protected synchronized T getObject() {
        if(! objects.isEmpty())
            return objects.poll();
        return createObject();
    }

    protected void close(T object){
        if(objects.size() > maxObject) {
            destroyObject(object);
        }else{
            objects.add(object);
        }
    }

    private void createMinObjects() {
        while(objects.size() < minObject){
            objects.add(createObject());
        }
    }

    abstract T createObject();
    abstract void destroyObject(T object);

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
