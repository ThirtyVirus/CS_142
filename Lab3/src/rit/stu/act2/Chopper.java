package rit.stu.act2;
import rit.cs.Stack;
import rit.stu.act1.StackNode;
/**
 * Created by ThirtyVirus on 2/14/2017.
 */
public class Chopper {

    //the passengers are stored in a stack
    private Stack<Player> chopper = new StackNode<>();;

    // The maximum number of passengers the chopper can hold
    static int	MAX_OCCUPANCY = 6;

    //the number of passengers occupying seats in the chopper
    private int	numPassengers;

    //the total number of passengers that have been flown away to safety and rescued
    private int	numRescued;

    Chopper(){
        numPassengers = 0;
        numRescued = 0;
    }

    //Board a passenger onto the chopper.
    void boardPassenger(Player player){
        if (isFull()){
            rescuePassengers();
        }
        else{
            chopper.push(player);
            numPassengers += 1;
            System.out.println(player + " boards the chopper!");
        }
    }

    //Get the total number of passengers that were rescued.
    int	getNumRescued(){
        return numRescued;
    }
    //Is the chopper empty?
    boolean	isEmpty(){
        return chopper.empty();
    }

    //Is the chopper full?
    boolean	isFull(){
        return numPassengers == MAX_OCCUPANCY;
    }

    //When the chopper is full, or it is the last group of people to be rescued, it will fly away and rescue the passengers.
    private void rescuePassengers(){
        //?
        if (isFull()){
            while(!chopper.empty()){
                System.out.println("Chopper transported " + chopper.pop() + " to safety");
            }
        }
    }


}
