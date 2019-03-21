package poly.stu;
import java.util.ArrayList;
/**
 * Created by Thirt on 1/30/2017.
 */
public class PolyDerive {

    /**
     Computes the Derivative given a polynomial
     Returns as an ArrayList
            poly (ArrayList): A polynomial represented as an ArrayList of numbers
     */
    public static ArrayList<Integer> computeDerivative(ArrayList<Integer> poly){
        ArrayList<Integer> Derivative = new ArrayList<Integer>();
            for (int counter =  1; counter < poly.size(); counter ++){
                Derivative.add(poly.get(counter) * counter);
            }

        if (Derivative.size() == 0){
                Derivative.add(0);
        }
        return Derivative;
    }
}
