package poly.stu;
import java.util.ArrayList;
/**
 * Created by Thirt on 1/30/2017.
 */
public class PolyRoot {

    static final int maxIter = 100;

    static final double initialGuess = 0.1;
    static final double epsilon = .0001; // + or - difference accepted

    /**
     Computes the root of a given polynomial
     Returns the root
        poly (ArrayList): A polynomial represented as an ArrayList of numbers
     */
    public static double computeRoot(ArrayList<Integer> poly){
        double root = 0;

        root = newtonsMethod(poly, initialGuess, 0);
        return root;
    }
    /**
     Uses Newton's Method to recursively estimate the root of a given polynomial
     Returns the root
        poly (ArrayList): A polynomial represented as an ArrayList of numbers
     */
    private static double newtonsMethod(ArrayList<Integer> poly, double x0, int iter){
        double root = PolyEval.evaluate(poly, x0);

        if (iter > maxIter || Math.abs(root) <= epsilon){
            return x0;
        }
        else{
            double x1 = x0 - root / PolyEval.evaluate(PolyDerive.computeDerivative(poly), x0);
            root = newtonsMethod(poly, x1, iter + 1);
        }

        return root;
    }
}
