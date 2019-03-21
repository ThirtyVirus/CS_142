/**
 * Created by Brandon Calabrese on 2/20/2017.
 *
 * Serves as a way to connect Appliances (and other receptacles) to a circuit
 */
public class Receptacle extends MultiComponent {

    //maximum number of children
    private int	maxChildren;

    //Constructor for a Receptacle.
    Receptacle(String name, Component source, int maxEl){
        super(name, source);
        maxChildren = maxEl;
    }

    //add a Component to this Receptacle.
    boolean	add(Component el){
        if ((el instanceof Receptacle || el instanceof Appliance) && children.size() < maxChildren){
            children.add(el);
            return true;
        }

        return false;
    }

    //Output a string representation of this Receptacle and its children.
    protected void display(String offset){
        System.out.println(offset + "Receptacle: " + name + " using " + currCurrent + " amps");
        for (Component c: children){
            c.display(offset + "\t");
        }
    }

}
