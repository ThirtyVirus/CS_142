import java.util.Comparator;

/**
 * EXPLANATION GOES HERE
 *
 * @author Brandon Calabrese
 */
public class Student extends User{

    //Create a new student.
    public Student(String username){
        super(username, UserType.STUDENT, (Course c1, Course c2) -> c1.getName().compareTo(c2.getName()));
    }

}
