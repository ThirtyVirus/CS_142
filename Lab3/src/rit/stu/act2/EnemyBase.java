package rit.stu.act2;
import rit.cs.Queue;
import rit.stu.act1.QueueNode;
import rit.cs.Stack;
import rit.stu.act1.StackNode;
import java.util.Random;
/**
 * Enemy Bas, handles freeing of Hostages and other misc tasks
 * Created by ThirtyVirus on 2/14/2017.
 */
public class EnemyBase extends Object {

    private Queue<Guerilla>	guerillas =  new QueueNode<>();
    private Stack<Hostage> hostages = new StackNode<>();
    private int	numGuerillas;
    private int	numHostages;

    public EnemyBase(int numHostages, int numGuerillas){
        this.numHostages = numHostages;
        this.numGuerillas = numGuerillas;

        for (int counter = 0 ; counter < numHostages ; counter++){
            hostages.push(new Hostage(counter + 1));
        }
        for (int counter = 0 ; counter < numGuerillas ; counter++){
            guerillas.enqueue(new Guerilla(counter + 1));
        }
    }

    //Add a guerilla to the end of the guard line.
    private void addGuerilla(Guerilla guerilla){
        guerillas.enqueue(guerilla);
    }

    //Add a hostage to the front of the cave.
    private void addHostage(Hostage hostage){
        hostages.push(hostage);
        numGuerillas += 1;
    }

    //Remove a guerilla from the front of the guard line.
    private Guerilla getGuerilla(){
        return guerillas.dequeue();
    }

    //Remove a hostage from the head of the cave.
    private Hostage	getHostage(){
        return hostages.pop();
    }

    //Get the number of guerillas in the guard line.
    int	getNumGuerillas(){
        return numGuerillas;
    }

    //Get the number of hostages in the cave.
    int	getNumHostages(){
        return numHostages;
    }

    //A soldier enters the enemy base and attempts to rescue a hostage.
    Hostage	rescueHostage(Soldier soldier){
        System.out.println(soldier + " enters enemy base");
        if (guerillas.empty()){
            return hostages.pop();
        }
        else{
            Guerilla guerilla = guerillas.dequeue();
            int chance = Battlefield.nextInt(1, 100);
            System.out.println(soldier + " battles " + guerilla + " who rolls a " + chance);
            if (chance > Guerilla.CHANCE_TO_BEAT_SOLDIER){
                soldier.victory(guerilla);
                guerilla.defeat(soldier);

                return hostages.pop();
            }
            else{
                guerilla.victory(soldier);
                soldier.defeat(guerilla);
                guerillas.enqueue(guerilla);
            }
        }
        return null;
    }


}
