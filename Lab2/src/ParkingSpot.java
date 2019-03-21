/**
 * Created by ThirtyVirus on 2/7/2017.
 */
public class ParkingSpot {

    private static String OCCUPIED_STR;
    private int spot;
    private Permit.Type type;
    private Vehicle vehicle;
    //Constructor
    ParkingSpot(int spot, Permit.Type type){this.spot = spot; this.type = type;}
    //Returns the spot
    public int getSpot(){return spot;}
    //Returns the type
    public Permit.Type getType(){return type;}
    //Returns the vehicle
    public Vehicle getVehicle(){return vehicle;}

    //Puts a vahicle in the spot
    public void occupySpot(Vehicle vehicle){this.vehicle = vehicle; this.vehicle.setParked(true);}

    //gets rid of a vehicle from a spot
    public void vacateSpot(){this.vehicle.setParked(false); this.vehicle = null;}

    //returns a String representation of a spot
    public String toString(){return (spot + ":" + type.toString());}

    //Verifies if the class works
    public static void verifySpot(String spotVar, ParkingSpot s, int spot, Permit.Type type, Vehicle vehicle){
        System.out.println("test1? " + (s.getSpot() == spot ? "OK" : "FAIL"));
        System.out.println("test2? " + (s.getType() == type ? "OK" : "FAIL"));

        if (s.getVehicle() == null){
            System.out.println("test3? " + (vehicle == null ? "OK" : "FAIL"));
        }
        else{
            System.out.println("test3? " + (s.getVehicle().equals(vehicle) ? "OK" : "FAIL"));
            System.out.println("test4? " + (s.getVehicle().isParked() ? "OK" : "FAIL"));
        }

        System.out.println(s.toString());
    }

    //Main class, calls test function
    public static void main(String[]args){
        ParkingSpot park = new ParkingSpot(1,Permit.Type.HANDICAPPED);
        verifySpot("",park,1,Permit.Type.HANDICAPPED,null);

        Vehicle v = new Vehicle(1234);
        park.occupySpot(v);
        verifySpot("",park,1,Permit.Type.HANDICAPPED,v);

        park.vacateSpot();
        verifySpot("",park,1,Permit.Type.HANDICAPPED,null);
    }


}
