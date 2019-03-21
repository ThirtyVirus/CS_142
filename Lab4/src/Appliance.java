/**
 * Created by Brandon Calabrese on 2/20/2017.
 *
 * An electrical Appliance
 */
public class Appliance extends Component{
    //Indicates if the device is currently in use.
    private boolean	inUse;

    //The required current (in amps) for this Appliance.
    private int	reqCurrent;

    Appliance(String name, Component source, int reqCurr){
        super(name, source);
        reqCurrent = reqCurr;
    }

    //This automatically returns false, as for this simulation, an Appliance can not be the source for any other Component.
    boolean	add(Component el){
        return false;
    }

    //Output a string representation for this Appliance, indenting by the given offset
    protected void display(String offset){
        System.out.println(offset + "Appliance: " + name + " using " + currCurrent + " amps");
    }

    //Accessor for whether device is currently in use.
    boolean	getInUse(){
        return inUse;
    }

    //Turns this appliance off.
    void reset(){
        inUse = false;
        currCurrent = 0;
    }

    //toggle usage on/off for this Appliance.
    String toggleUsage(){

        if (inUse){
            inUse = false;

            Component parent = this;
            while (parent != null){
                parent.updateCurrent(- 1 * reqCurrent);
                parent = parent.source;
            }

            return null;
        }
        else{
            inUse = true;
            String over = null;
            Component parent = this;

            while (parent != null){
                String overload = parent.updateCurrent(reqCurrent);

                if (overload != null){
                    over = overload;
                }
                parent = parent.source;
            }
            if (over != null){
                return over;
            }
            return null;
        }
    }


}
