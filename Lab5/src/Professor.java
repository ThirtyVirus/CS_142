import java.util.Comparator;

/**
 * EXPLANATION GOES HERE
 *
 * @author Brandon Calabrese
 */
public class Professor extends User{

    //Create a new professor.
    public Professor(String username){
        super(username, UserType.PROFESSOR, (Course c1, Course c2) -> c1.getName().compareTo(c2.getName()));
    }

}
