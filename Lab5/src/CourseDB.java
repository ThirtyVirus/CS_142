import java.util.*;

/**
 * EXPLANATION GOES HERE
 *
 * @author Brandon Calabrese
 */
public class CourseDB extends Object implements DB<Integer,Course> {

    //course storage
    private HashMap<Integer,Course> courses = new HashMap<Integer,Course>();

    //Create the Course database.
    public CourseDB(){
        //so far nothing needed here
    }

    //Add a value entry to the database in constant time.
    public Course addValue(Course course){

        Course test = null;
        if (courses.containsKey(course.getId())){
            courses.put(course.getId(),course);
            test = courses.get(course.getId());
        }

        courses.put(course.getId(),course);
        if (test == null){
            return null;
        }
        else{
            return test;
        }

    }

    //Get all the values in the database in linear time.
    public Collection<Course> getAllValues(){
        List<Course> u = new ArrayList<Course>(this.courses.values());
        Collections.sort(u);
        return u;
    }

    //Get the value for an associated key in constant time.
    public Course getValue(Integer id){
        return courses.get(id);
    }

    //Indicates whether a key is in the database or not, in constant time.
    public boolean hasKey(Integer id){
        return courses.containsKey(id);
    }


}
