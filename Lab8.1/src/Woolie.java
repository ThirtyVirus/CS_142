/**
 * Created by danielshen on 4/25/17.
 */

import java.util.Random;
public class Woolie {

    private static int seed = 1;
    Random randomAttack = new Random();

    public String name;
    public int minAttack;
    public int maxAttack;
    public int frequency;
    public int maxHealth;
    public int health;

    public Woolie(String name, int minAttack, int maxAttack, int frequency, int maxHealth){
        /**

         */
        randomAttack.setSeed(seed);

        this.name = name;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.frequency = frequency;
        this.maxHealth = maxHealth;
        health = maxHealth;

    }

    public Woolie(String[] params){
        /**

         */

        randomAttack.setSeed(seed);
        name = params[0];
        minAttack = Integer.parseInt(params[1]);
        maxAttack = Integer.parseInt(params[2]);
        frequency = Integer.parseInt(params[3]);
        maxHealth = Integer.parseInt(params[4]);
        health = maxHealth;

    }
    public String getName(){
        /**
         *
         Returns:
         */

        return name;
    }

    public int getCurrentHP(){
        /**
         *
         Returns:
         */

        return health;
    }

    public int getAttackAmount(){
        /**
         *
         Returns:
         */

        return randomAttack.nextInt(maxAttack - minAttack) + minAttack;
    }

    public void takeDamage(int damage){
        /**

         */

        health = health - damage;
    }

    public boolean isOK(){
        /**
         *
         Returns:
         */

        if (health <= 0) return false;
        return true;
    }

    public int getHitTime(){
        /**
         *
         Returns:
         */

        return frequency;
    }

    public void reset(){
        /**

         */

        health = maxHealth;
    }

    @Override
    public String toString() {
        /**

         */

        return "Woolie{" +
                "name='" + name + '\'' +
                ", minAtk=" + minAttack +
                ", maxAtk=" + maxAttack +
                ", hitTime=" + frequency +
                ", maxHP=" + maxHealth +
                ", currentHP=" + health +
                '}';
    }

}
