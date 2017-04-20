import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Bishal Paudel on 4/19/17.
 *
 * Interesting features:
 * 1. This class cannot be subclassed because constructor is private.
 * 2. If constructor was protected, it can be subclassed but may have many instances (in a package).
 * 3. If different classLoaders are used, then this class may have different instances.
 * 4. If this class implements Serializable, and its instances are serialized and deserialized,
 *    then this class may have many instances after deserialization.
 * 5. This is not threadsafe as Thread1 may be at if condition and thread2 may have just initialized the pool with pool = new DBConnectionPool();.
 *    For this we may synchronize getInstance as: public synchronized static DBConnectionPool getInstance(){...}
 *    The whole thread unsafe issue is only for the first singleton creation, and synchonized may be 100 times slower.
 * 6. We can have: public static DBConnectionPool INSTANCE = new DBConnectionPool(); Its usage would be DBConnectionPool pool = DBConnectionPool.INSTANCE;
 *    But this is not lazy loading, which is created at compile time.
 *
 * 7. Best would be to use Registry to hold singleton's instances and use reflection. NEXT TIME!!!
 *
 *  Inspired from: http://www.javaworld.com/article/2073352/core-java/simply-singleton.html
 */
public class DBConnectionPool extends AbstractPool<Connection> {

    private static DBConnectionPool pool;

    private DBConnectionPool() throws Exception {
        /* To defeat instantiation. */
        super();
        pool = new DBConnectionPool();
    }

    public static DBConnectionPool getInstance(){
        return pool;
    }

/* Issue 5 address this code block */
//    public synchronized static DBConnectionPool getInstance(){
//        if(pool == null)
//            pool = new DBConnectionPool();
//        return pool;
//    }

    /* To solve the 3rd issue: If different classLoaders are used, then this class may have different instances. */
    private static Class getClass(String className) throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null){
            classLoader = DBConnectionPool.class.getClassLoader();
        }
        return (classLoader.loadClass(className));
    }


    /* To solve the 4th issue: If this class implements Serializable, and its instances are serialized and deserialized,
    *  then this calss may have many instances after deserialization.
    */
    private Object readResolve(){
        return pool;
    }


    Connection createObject() throws SQLException {
        return DriverManager.getConnection(DataSource.getURL());
    }
}
