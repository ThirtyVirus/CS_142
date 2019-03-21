import java.util.*;

/**
 * EXPLANATION GOES HERE
 *
 * @author Brandon Calabrese
 */
public class UserDB extends Object implements DB<String,User> {

    //user storage
    private HashMap<String,User> users = new HashMap<String,User>();

    //Create the user database.
    public UserDB(){
    //so far nothing needed here
    }

    //Add a value entry to the database in constant time.
    public User addValue(User user){
        User test = null;
        if (users.containsKey(user.getUsername())){
            users.put(user.getUsername(),user);
            test = users.get(user.getUsername());
        }

        users.put(user.getUsername(),user);

        if (test == null){
            return null;
        }
        else{
            return test;
        }

    }

    //Get all the values in the database in linear time.
    public Collection<User> getAllValues(){
        List<User> u = new ArrayList<>(this.users.values());
        Collections.sort(u);
        return u;
    }

    //Get the value for an associated key in constant time.
    public User getValue(String username){
        return users.get(username);
    }

    //Indicates whether a key is in the database or not, in constant time.
    public boolean hasKey(String username){
        return users.containsKey(username);
    }



}
