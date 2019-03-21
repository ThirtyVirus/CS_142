/**
 * Created by Brandon Calabrese on 2/20/2017.
 *
 * Class representing an electrical circuit
 */
public class Circuit extends MultiComponent{

    //maximum capacity for this circuit (amps) before it is overloaded.
    private int	maxCapacity;

    //Constructor for a Circuit.
    Circuit(String name, Component source, int maxCapacity){
        super(name, source);
        this.maxCapacity = maxCapacity;
    }

    //add a Component to this circuit.
    boolean	add(Component el){
        if (el instanceof Receptacle || el instanceof Circuit){
            children.add(el);
            return true;
        }
        return false;
    }

    //Output a string representation of this Circuit and its children.
    protected void display(String offset){
        System.out.println(offset + "Circuit: " + name + " using " + currCurrent + " out of " + maxCapacity + " available amps");
        for (Component c: children){
            c.display(offset + "\t");
        }
    }

    //Update current usage within this Circuit.
    protected String updateCurrent(int deltaCurrent){
        currCurrent += deltaCurrent;
        if (currCurrent > maxCapacity){
           return name;
        }
        return null;
    }



}
