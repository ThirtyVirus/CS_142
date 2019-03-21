package rit.stu.act2;

/**
 * Soldier class, defines a soldier that fights the guerillas
 * Created by ThirtyVirus on 2/14/2017.
 */
public class Soldier extends Object implements Player{

    private int id;

    public Soldier(int id){
        this.id = id;
    }

    //prints defeat
    public void defeat(Player player){
        System.out.println(toString() + " cries, 'Besiegt von ! " + player.toString() +"'");
    }

    //prints string representation of soldier
    public String toString(){
        return "Soldier #" + id;
    }

    //prints victory
    public void victory(Player player){
        System.out.println(toString() + " yells, 'Sieg über "  + player.toString() +"!'");
    }

}


