import java.util.ArrayList;

/**
 * Created by Brandon Calabrese on 2/20/2017.
 *
 * Connects Component class to other classes
 */
public abstract class MultiComponent extends Component{

    //list of all children connected to this Component
    protected ArrayList<Component> children = new ArrayList<Component>();

    //Basic constructor for MultiComponent object.
    protected MultiComponent(String name, Component source){
        super(name,source);
    }

    //Reset this MultiComponent to have a current usage of 0, and call reset on all children.
    void reset(){
        currCurrent = 0;
        for (Component c: children){
            c.reset();
        }
    }


}
