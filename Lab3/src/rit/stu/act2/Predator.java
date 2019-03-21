package rit.stu.act2;

/**
 * Predator class, defines the predator which will be a general annoyance for the soldiers
 * Created by ThirtyVirus on 2/14/2017.
 */
public class Predator extends Object implements Player {
    public static final int CHANCE_TO_BEAT_HOSTAGE = 75;
    public static final int CHANCE_TO_BEAT_SOLDIER = 50;

    public Predator(){}

    //prints defeat
    public void defeat (Player player){
        System.out.println("The predator cries out in glorious defeat to " + player.toString());
    }

    //prints the string representation of the predator
    public String toString(){
        return "Predator";
    }

    //prints victory
    public void victory(Player player){
        System.out.println("The predator yells out in triumphant victory over " + player.toString());
    }

}
