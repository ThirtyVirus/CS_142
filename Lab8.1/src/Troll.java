/**
 * Created by danielshen on 4/25/17.
 */

import java.util.ArrayList;
import java.util.Random;

public class Troll {

    public static int seed = 1;
    Random randomNumber = new Random();

    public SportsComplex sc;
    public ArrayList<Woolie> wool;


    public Troll(ArrayList<Woolie> woolies,
                 SportsComplex sportsComplex){
        /**
         * Troll Constructor.

         Parameters:
         woolies - The list of Woolies that will battle.
         sportsComplex - The sports complex the battle will occur in.
         */
        randomNumber.setSeed(seed);

        wool = woolies;
        sc = sportsComplex;

    }

    public synchronized void beginBattleRoyale(){
        /**
         * This tells the troll to begin the Battle Royale
         with the current list of Woolies.
         */

        for (int round = 1; wool.size() > 1; round = round + 1){

            System.out.println("Round " + round + " is about to begin!");
            System.out.println("The contestants for this round are:");
            for (Woolie w : wool){
                System.out.println("        "+w.toString());
            }
            System.out.println();

            ArrayList<WoolieBattleThread> arenas = new ArrayList<>();
            while (wool.size() > 1){

                //get 2 random woolies
                Woolie fighter1 = wool.get(randomNumber.nextInt(wool.size()));
                Woolie fighter2 = wool.get(randomNumber.nextInt(wool.size()));

                wool.remove(fighter1);
                wool.remove(fighter2);

                //make new arena for woolies
                WoolieBattleThread arena = new WoolieBattleThread(fighter1,fighter2, sc);
                arena.start(); arenas.add(arena);

                try {
                    arena.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            //have battles and get winners
            for (WoolieBattleThread arena : arenas){
                Woolie winner = arena.getWinner();
                winner.reset(); wool.add(winner);

            }

            System.out.println("Round " + round + " has ended!");
            System.out.println("The contestents left after this round are:");
            for (Woolie woolie : wool){
                System.out.println("        " + woolie.toString());
            }
            System.out.println();
        }
        System.out.println("The winner is " + wool.get(0).getName());

    }
}
