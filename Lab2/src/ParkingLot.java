/**
 * Created by ThirtyVirus on 2/7/2017.
 */
import java.util.ArrayList;

public class ParkingLot {

    private int	capacity;
    private int	generalSpots;
    private int	handicappedSpots;
    static int	ILLEGAL_SPOT;
    private ArrayList<ParkingSpot> lot = new ArrayList<ParkingSpot>();
    private int	parkedVehicles;
    private int	reservedSpots;
    private static int SPOTS_PER_LINE = 9;
    //Constructor, calls initialize
    ParkingLot(int handicappedSpots, int reservedSpots, int generalSpots){
        this.handicappedSpots = handicappedSpots;
        this.reservedSpots = reservedSpots;
        this.generalSpots = generalSpots;
        capacity = handicappedSpots + reservedSpots + generalSpots;
        initializeSpots();
    }
    int	getCapacity(){return capacity;}
    int	getNumParkedVehicles(){return parkedVehicles;}
    ParkingSpot	getSpot(int spot){return lot.get(spot);}

    //Adds the parking spaces to the lot
    private void initializeSpots(){
        int progress = 0;
        for (int counter = 0 ; counter < handicappedSpots ; counter ++){
            lot.add(new ParkingSpot(progress,Permit.Type.HANDICAPPED));
            progress += 1;
        }
        for (int counter = 0 ; counter < reservedSpots ; counter ++){
            lot.add(new ParkingSpot(progress,Permit.Type.RESERVED));
            progress += 1;
        }
        for (int counter = 0 ; counter < generalSpots ; counter ++){
            lot.add(new ParkingSpot(progress,Permit.Type.GENERAL));
            progress += 1;
        }
    }

    //Returns whether the spot is vacant
    boolean	isSpotVacant(int spot){return (lot.get(spot).getVehicle() == null);}
    //Returns if a spot is valid
    boolean	isSpotValid(int spot){return (spot >= 0 && spot < capacity);}

    //Parks a vehicle
    boolean	parkVehicle(Vehicle vehicle, int spot){
        if (lot.get(spot).getVehicle() == null){
            ParkingSpot newSpot = lot.get(spot);
            newSpot.occupySpot(vehicle);
            lot.set(spot, newSpot);
            return true;
        }
        return false;
    }

    //Removes a vehicle from a spot
    int	removeVehicle(Vehicle vehicle){
        for (int counter = 0 ; counter < capacity ; counter ++) {
            if (lot.get(counter).getVehicle() == vehicle){
                lot.get(counter).vacateSpot();
                return counter;
            }
        }
        return ILLEGAL_SPOT;
    }

    //Retruns a string representation of a parking lot.
    public String toString(){
        String sum = "";
        int rowCount = 0;
        int vacantSpots = 0;

        for (int counter = 0 ; counter < capacity ; counter ++) {
            sum += lot.get(counter).toString() + " ";
            if (lot.get(counter).getVehicle() == null){
                vacantSpots += 1;
            }

            if (rowCount == SPOTS_PER_LINE){
                rowCount = 0;
                sum += "\n";
            }
            else{
                rowCount++;
            }
        }
        sum += "Vacant Spots: " + vacantSpots;

        return sum;
    }

    public static void	main(String[] args){

    }

}
