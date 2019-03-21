import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * EXPLANATION GOES HERE
 *
 * @author Brandon Calabrese
 */

public class User extends Object implements Comparable<User>{

    /** The type of user */
    public enum UserType {
        PROFESSOR,
        STUDENT
    }

    //The courses the professor is teaching, or student is enrolled in
    private TreeSet<Course> courses;

    //The user type
    private User.UserType type;

    //The username (unique)
    private String username;


    //Create a new user.
    public User(String username, User.UserType type, Comparator<Course> comp){
        this.username = username;
        this.type = type;
        this.courses = new TreeSet<Course>(comp);
    }

    //Add a course for this user.
    public boolean addCourse(Course course){

        if (courses.contains(course)){
            return false;
        }
        else{
            courses.add(course);
            return true;
        }
    }

    //Users are naturally ordered alphabetically by username.
    public int compareTo(User other){
        return this.getUsername().compareTo(other.getUsername());
    }

    //Two users are equal if they have the same username.
    @Override
    public boolean equals(Object other){
        if (other instanceof User){
            User u = (User)(other);
            return this.getUsername().equals(u.getUsername());
        }
        return false;
    }

    //Returns the courses the user is currently teaching or enrolled in.
    public Collection<Course> getCourses(){
        return courses;
    }

    //Get the user type.
    public User.UserType getType(){
        return type;
    }

    //Get the username.
    public String getUsername(){
        return username;
    }

    //The hash code of a user is the hash code of the username.
    @Override
    public int hashCode(){
        return username.hashCode();
    }

    //Remove of a course for this user.
    public boolean removeCourse(Course course){
        if (courses.size() == 0 || !courses.contains(course)){
            return false;
        }
        else{
            courses.remove(course);
            return true;
        }
    }

    //Returns a string representation of the user in the format:
    //User{username=USERNAME, type=TYPE, courses=COURSE_LIST}
    //Here, COURSE_LIST is a list of courses, with brackets surrounding the comma separated entries, which are the course names in ascending alphabetical order for students, and first by course level and second by ascending alphabetical course name for professors.
    @Override
    public String toString(){
        Collection<String> courseNames = new ArrayList<String>();
        for (Course c : courses){
            courseNames.add(c.getName());
        }
        return ("User{username='" + getUsername() + "', type=" + getType() + ", courses=" + courseNames + "}");
    }

}
