import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * EXPLANATION GOES HERE
 *
 * @author Brandon Calabrese
 */
public class Backend extends Object{

    //the database of courses
    private CourseDB courseDB = new CourseDB();

    //the database of users
    private UserDB userDB = new UserDB();

    //Creates the backend by initializing the course and user databases.
    public Backend(String courseFile, String professorFile, String studentFile) throws FileNotFoundException {
        initializeCourseDB(courseFile);
        initializeUserDB(professorFile, studentFile);
    }

    //A utility "utility" method.
    private void addCourses(User user, String[] courseIds){
        for (int counter = 0; counter < courseIds.length; counter ++){
            user.addCourse(getCourse(Integer.parseInt(courseIds[counter])));
        }
    }

    //Check whether a course exists or not.
    public boolean courseExists(int id){
        return (courseDB.getValue(id) != null);
    }

    //Enroll a student in a course.
    public boolean enrollStudent(String username, int courseId){
        if (userDB.getValue(username).getCourses().contains(courseDB.getValue(courseId))){
            return false;
        }
        else{
            userDB.getValue(username).addCourse(courseDB.getValue(courseId));
            courseDB.getValue(courseId).addStudent(username);
            return true;
        }
    }

    //Get all the courses in the system.
    public Collection<Course> getAllCourses(){
        return courseDB.getAllValues();
    }

    //Get all users in the system.
    public Collection<User> getAllUsers(){
        return userDB.getAllValues();
    }

    //Get a course by id.
    public Course getCourse(int id){
        return courseDB.getValue(id);
    }

    //Get the courses for a particular user.
    public Collection<Course> getCoursesUser(String username){
        return userDB.getValue(username).getCourses();
    }

    //A utility method for initializing the course database.
    private void initializeCourseDB(String courseFile) throws FileNotFoundException{
        //Course File
        try (Scanner in = new Scanner(new File(courseFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");
                courseDB.addValue(new Course(Integer.parseInt(fields[0]),fields[1],Integer.parseInt(fields[2])));
            }
        }
    }

    //A utility method for initializing the user database.
    private void initializeUserDB(String professorFile, String studentFile) throws FileNotFoundException{

        //Professor File
        try (Scanner in = new Scanner(new File(professorFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");

                Professor newProf = new Professor(fields[0]);
                addCourses(newProf, Arrays.copyOfRange(fields,1,fields.length));

                for (Course c : newProf.getCourses()){
                    c.addProfessor(newProf.getUsername());
                }

                userDB.addValue(newProf);
            }
        }

        //Student File
        try (Scanner in = new Scanner(new File(studentFile))) {
            while (in.hasNext()) {
                String[] fields = in.nextLine().split(",");

                Student newStud = new Student(fields[0]);
                addCourses(newStud, Arrays.copyOfRange(fields,1,fields.length));

                for (Course c : newStud.getCourses()){
                    c.addStudent(newStud.getUsername());
                }

                userDB.addValue(newStud);
            }
        }
    }

    //Check whether a username belongs to a student.
    public boolean isStudent(String username){
        return (userDB.getValue(username).getType() == User.UserType.STUDENT);
    }

    //Unenroll a student in a course.
    public boolean unenrollStudent(String username, int courseId){
        if (!userDB.getValue(username).getCourses().contains(courseDB.getValue(courseId))){
            return false;
        }
        else{
            userDB.getValue(username).removeCourse(courseDB.getValue(courseId));
            courseDB.getValue(courseId).removeStudent(username);
            return true;
        }
    }

    //Check if a username exists in the system.
    public boolean userExists(String username){
        return (userDB.getValue(username) != null);
    }


}
