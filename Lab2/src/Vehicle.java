import java.util.ArrayList;

/**
 * Represents a vehicle in the parking simulator.
 *
 * @author Sean Strout @ RIT CS
 * @author YOUR Brandon Calabrese
 */
public class Vehicle {

    private int plate;
    private boolean parked;
    private Permit permit;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public static void main(String[] args) {
        // create a Vehicle, v1, whose plate number is 10, is unparked, with no
        // permit and no tickets (an empty list).
        Vehicle v1 = new Vehicle(10);

        // call the following operations on v1 and print each result:
        //      getPlate() -> 10
        //      isParked() -> false
        //      hasPermit() -> false
        //      getPermit() -> null
        //      getTickets() -> []
        System.out.println("v1 plate is 10? " + (10 == v1.getPlate() ? "OK" :
                "FAIL, got: " + v1.getPlate()));
        System.out.println("v1 is not parked? " + (!v1.isParked() ? "OK" : "FAIL"));
        System.out.println("v1 has no permit? " + (!v1.hasPermit() ? "OK" : "FAIL"));
        System.out.println("v1 permit is null? " + (v1.getPermit() == null ? "OK" :
                "FAIL, got: " + v1.getPermit()));
        System.out.println("v1 has no tickets? " + (v1.getTickets() != null && v1.getTickets().size() == 0 ?
                "OK" : "FAIL, got: " + v1.getTickets()));

        // check's Vehicle's toString(), you should get:
        //      Vehicle{plate=10, permit=null, parked=false, tickets=[]}
        System.out.println("v1 toString?: " +
            (v1.toString() != null && v1.toString().equals("Vehicle{plate=10, permit=null, parked=false, tickets=[]}") ?
                    "OK" : "FAIL, got: " + v1.toString()));

        // create a second Vehicle v2, whose plate number is 20, park it, test
        // it is parked and then its toString(), you should get:
        //      v2: Vehicle{plate=20, permit=null, parked=true, tickets=[]}
        Vehicle v2 = new Vehicle(20);
        v2.setParked(true);
        System.out.println("v2 toString?: " +
                (v2.toString() != null && v2.toString().equals("Vehicle{plate=20, permit=null, parked=true, tickets=[]}") ?
                        "OK" : "FAIL, got: " + v2.toString()));

        // create a third Vehicle v3, whose plate number is also 20 test it's
        // toString() is:
        //      v3: Vehicle{plate=20, permit=null, parked=false, tickets=[]}
        Vehicle v3 = new Vehicle(20);
        System.out.println("v3 toString?: " +
                (v3.toString() != null && v3.toString().equals("Vehicle{plate=20, permit=null, parked=false, tickets=[]}") ?
                        "OK" : "FAIL, got: " + v3.toString()));

        // verify that v2 and v3 are equal because they have the same plate number:
        System.out.println("v2 equals v3? " + (v2.equals(v3) ? "OK" : "FAIL"));

        // verify that v1 and v2 are not equal:
        System.out.println("v1 does not equal v3? " + (!v1.equals(v3) ? "OK" : "FAIL"));

        // verify that v1 is not equal to the string, "v1":
        System.out.println("v1 does not equal \"v1\"? " + (!v1.equals("v1") ? "OK" : "FAIL"));

        // issue a handicapped permit, p1, to v1 and make sure v1 has the permit
        Permit p1 = new Permit(1, Permit.Type.HANDICAPPED);
        v1.setPermit(p1);
        System.out.println("v1 with permit p1? " + (v1.getPermit() != null && v1.getPermit().equals(p1) ? "OK" : "FAIL"));

        // give a ticket, t2, to v2 for parking without a permit and verify it has it
        Ticket t1 = new Ticket(v2.getPlate(), Fine.NO_PERMIT);
        v2.giveTicket(t1);
        System.out.println("v2 with ticket t1? " + (v2.getTickets() != null &&
                v2.getTickets().get(0).equals(t1) ? "OK" : "FAIL"));

        // give another ticket, t3, to v2 and verify it has it as well
        Ticket t2 = new Ticket(v2.getPlate(), Fine.PARKING_HANDICAPPED);
        v2.giveTicket(t2);
        System.out.println("v2 with another ticket t2? " + (v2.getTickets() != null && v2.getTickets().get(1).equals(t2) ?
                "OK" : "FAIL"));
    }
    //Constructor, sets plate
    public Vehicle(int p){plate = p;}
    //Returns the Vehicle's plate
    public int getPlate(){return plate;}
    //Returns parked
    public boolean isParked(){return parked;}
    //Sets parked to p
    public void setParked(boolean p){parked = p;}
    //returns if vehicle has permit
    public boolean hasPermit(){if (permit != null){return true;} else{return false;}}
    //gets the vehicle's permit
    public Permit getPermit(){return permit;}
    //sets the vehicle's permit
    public void setPermit(Permit p){permit = p;}
    //returns tickets
    public ArrayList<Ticket> getTickets(){return tickets;}
    //adds to the tickets list
    public void giveTicket(Ticket t){tickets.add(t);}
    //tests if equal
    public boolean equals(Object other){
        if (other instanceof Vehicle){
            if (((Vehicle)other).plate == plate){
                return true;
            }
        }
        return false;
    }
    //Returns string representation of vehicle
    public String toString(){
        return "Vehicle{plate=" + plate + ", permit=" + permit + ", parked=" + parked + ", tickets=" + tickets + "}";
    }
}