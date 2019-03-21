package rit.stu.act2;

/**
 * Guerilla Class, defines the guerillas that fight the soldiers
 * Created by ThirtyVirus on 2/14/2017.
 */
public class Guerilla extends Object implements Player{
    public static final int CHANCE_TO_BEAT_SOLDIER = 20;
    private int id;

    public Guerilla(int id){
        this.id = id;
    }

    //prints defeat
    public void defeat(Player player){
        System.out.println(toString() + " cries, 'Derrotado por " + player.toString() +"'");
    }

    //prints string representation of guerilla
    public String toString(){
        return "Guerilla #" + id;
    }

    //Prints victory
    public void victory(Player player){
        System.out.println(toString() + " yells, 'Victoria sobre"  + player.toString() + "'");
    }

}
