package rit.stu.act2;
import rit.cs.Queue;
import rit.stu.act1.QueueNode;
/**
 * Bunker class, defines the bunker that holds the soldiers.
 * Created by ThirtyVirus on 2/14/2017.
 */
public class Bunker extends Object{
private Queue<Soldier> bunker = new QueueNode<>();
private int numSoldiers;

//constructor, sets values for bunker
    public Bunker(int numSoldiers){
        this.numSoldiers = numSoldiers - 1;

        for(int counter = 0 ; counter < numSoldiers; counter++){
            bunker.enqueue(new Soldier(counter + 1));
        }
    }

    //deploys a soldier from the bunker
    public Soldier deployNextSoldier(){
        return bunker.dequeue();
    }

    //adds a soldier to the bunker
    void fortifySoldiers(Soldier soldier){
        bunker.enqueue(soldier);
        numSoldiers += 1;
    }

    //returns the number of soldiers in the bunker
    int	getNumSoldiers(){
        return numSoldiers;
    }

    //returns whether or not the bunker is empty
    boolean	hasSoldiers(){
        return !bunker.empty();
    }

}
