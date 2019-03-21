/**
 * Created by Brandon Calabrese on 2/20/2017.
 *
 * Abstract base class for electricity lab
 * This class is used to represent any component of the overall electrical system
 */
public abstract class Component extends Object{

    //current being drawn at this moment by this object.
    protected int currCurrent;

    //name associated with this component.
    protected String name;

    //source of this component.
    protected Component	source;

    //Basic constructor for an electrical Component involves a name and a source.
    protected Component(String name, Component source){
        this.name = name;
        this.source = source;
        currCurrent = 0;
    }

    //Add a new electrical Component as a child of this element.
    abstract boolean add(Component newElem);

    //Output a string representation of this Component and its children.
    void display(){
        display("");
    }

    //Output a string representation of the given electrical Component and its children.
    protected abstract void	display(String offset);

    //Reset this object along with all of its children.
    abstract void reset();

    //Update current.
    protected String updateCurrent(int deltaCurrent){
        currCurrent += deltaCurrent;
        return null;
    }


}
