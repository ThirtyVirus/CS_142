package poly.stu;
import java.util.ArrayList;

/**
 * Created by Thirt on 1/25/2017.
 */
public class PolyEval {

    /**
     Tests if a given polynomial is valued as zero
     Returns a boolean
        poly (ArrayList): A polynomial represented as an ArrayList of numbers
     */
    public static boolean isZero(ArrayList<Integer> poly){

        for (int counter = 0; counter < poly.size(); counter ++){
            if (poly.get(counter) != 0) {
                return false;
            }
        }
        return true;

    }

    /**
     Evaluates a polynomial given a value for x
     Returns a boolean
        poly (ArrayList): A polynomial represented as an ArrayList of numbers
        x (double): The value of x, used to evaluate polynomial
     */
    public static double evaluate(ArrayList<Integer> poly, double x){
        double answer = 0;
        for (int counter = 0; counter < poly.size(); counter++){
            answer += poly.get(counter) * Math.pow(x,counter);
        }

        return answer;
    }


}
