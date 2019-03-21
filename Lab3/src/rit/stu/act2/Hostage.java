package rit.stu.act2;

/**
 * *Hostage Class, defines a hostage to be rescued
 * Created by ThirtyVirus on 2/14/2017.
 */
public class Hostage extends Object implements Player{
    private int id;

    //constructor, sets id
    public Hostage(int id){
        this.id = id;
    }

    //prints defeat
    public void defeat(Player player){
        System.out.println(toString() + " cries, 'Defeated by " + player.toString() + "!'");
    }

    //prints string representation of hostage
    public String toString(){
        return "Hostage #" + id;
    }

    //prints victory
    public void victory(Player player){
        System.out.println(toString() + " yells, 'Victory over " + player.toString() + "!'");
    }

}
