/**
 * Created by danielshen on 4/25/17.
 */

import java.util.ArrayList;
class WoolieBattleThread extends Thread {

    public SportsComplex sportsComplex;
    private Woolie fighter1;
    private Woolie fighter2;


	public WoolieBattleThread(Woolie fighter1,
							  Woolie fighter2,
							  SportsComplex sportsComplex){
		/**
		 *
		 WoolieBattleThread constructor

		 Parameters:
		 fighter1 - A woolie to do battle
		 fighter2 - Another woolie to do battle
		 sportsComplex - The sports complex the battles are occurring in.
		 */

        this.sportsComplex = sportsComplex;
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
	}

	public Woolie getFighter1(){
		/**
		 * A getter for the first fighter.

		 Returns:
		 the first fighter (Woolie)
		 */
		return fighter1;
	}

	public Woolie getFighter2(){
		/**
		 * A getter for the second fighter.
		 Returns:
         the second fighter (Woolie)
		 */
		return fighter2;
	}

	@Override
	public void run(){
		/**
		 * Starts the battle between the two Woolies. Ends when one Woolie is "knocked out". This is the run method for the thread.
		 Specified by: run in interface Runnable
		 Overrides: run in class Thread
		 */

        enterArena();

        System.out.println("The battle has begun between " + fighter1.getName() + " and " + fighter2.getName());

        for (int time = 0; getWinner() == null; time = time + 1){
            //cant attack at 0 seconds
            if (time > 0){

                //fighter1 attack
                if (fighter1.isOK()) {
                    if (time % fighter1.getHitTime() == 0) {
                        int attack = fighter1.getAttackAmount(); fighter2.takeDamage(attack);

                        System.out.println(fighter1.getName() + " does " + attack + " damage to " + fighter2.getName());
                        System.out.println(fighter2.getName() + " has " + fighter2.getCurrentHP() + " left");
                        System.out.println();

                    }
                }

                //fighter2 attack
                if (fighter2.isOK()) {
                    if (time % fighter2.getHitTime() == 0) {
                        int attack = fighter2.getAttackAmount(); fighter1.takeDamage(attack);

                        System.out.println(fighter2.getName() + " does " + attack + " damage tp " + fighter1.getName());
                        System.out.println(fighter1.getName() + " has " + fighter1.getCurrentHP() + " left");
                        System.out.println();

                    }
                }
            }

            //sleep 1 second
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("The battle is over!");
        System.out.println(getWinner().getName() + " is the winner!\n");

        exitArena();
	}

	public Woolie getWinner(){
		/**
		 * Returns the winner. Assumes the battle
		 is over when this is called.

		 Returns:
		 Will return null if the battle is not over. The winner otherwise.
		 */

		//is 1 ok?
        if (fighter1.isOK() == false){
            return fighter2;
        }
        //is 2 ok?
        else if (fighter2.isOK() == false){
            return fighter1;
        }
        //both are ok
        else{
            return null;
        }
	}

	public void enterArena(){
		/**
		 * Battle is entering the arena. Called when the
		 battle is requesting an arena to do battle in.
		 Will request entry to an arena from the sports
		 complex.
		 */

		//print that woolie is entering arena
        System.out.println("WOOLIES: " + fighter1.getName() + " and " + fighter2.getName() + " enterArena line to battle.");
        sportsComplex.enterArena(this);
        System.out.println("WOOLIES: " + fighter1.getName() + " and " + fighter2.getName() + " enterArena arena to battle.");
	}

	public void exitArena(){
		/**
		 * Battle is exiting the arena. Called when the battle has finished and it is giving up control of the arena.
		 * It will tell the sports complex it is leaving the arena
		 */

        //print that woolie is exiting arena
        System.out.println("WOOLIE: " + getWinner().getName() + " leaves arena victorious!");
        sportsComplex.leaveArena();
	}
}
