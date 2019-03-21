import java.util.Collection;

/**
 * EXPLANATION GOES HERE
 *
 * @author Brandon Calabrese
 */
public interface DB<K,V> {

    //Add a value entry to the database in constant time.
    V addValue(V value);

    //Get all the values in the database in linear time.
    Collection<V> getAllValues();

    //Get the value for an associated key in constant time.
    V getValue(K key);

    //Indicates whether a key is in the database or not, in constant time.
    boolean	hasKey(K key);
}
