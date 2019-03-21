/**
 * Created by ThirtyVirus on 2/7/2017.
 */
import java.util.ArrayList;

public class ParkingOfficer {
    private ParkingLot lot;
    private static int PAUSE_TIME;
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    ParkingOfficer(){

    }

    //Returns the fine for a cvertain vehicle depending on their permit and parking spot
    static Fine	getFineVehicleSpot(Vehicle vehicle, ParkingSpot spot){

        if (vehicle.isParked()){
            if (vehicle.getPermit() == null && spot.getType() == Permit.Type.GENERAL){
                return Fine.NO_PERMIT;
            }

            if (spot.getType() == Permit.Type.HANDICAPPED){
                if (vehicle.getPermit() == null){
                    return Fine.PARKING_HANDICAPPED;
                }
                if (vehicle.getPermit().getType() != Permit.Type.HANDICAPPED){
                    return Fine.PARKING_HANDICAPPED;
                }
            }
            if (spot.getType() == Permit.Type.RESERVED){
                if (vehicle.getPermit() == null){
                    return Fine.PARKING_RESERVED;
                }
                if (vehicle.getPermit().getType() != Permit.Type.HANDICAPPED && vehicle.getPermit().getType() != Permit.Type.RESERVED){
                    return Fine.PARKING_RESERVED;
                }
            }
        }
        return Fine.NO_FINE;
    }
    //Returns tickets
    ArrayList<Ticket> getTickets(){return tickets;}

    private void issueTicket(Vehicle vehicle, int spot, Fine fine){
        vehicle.giveTicket(new Ticket(vehicle.getPlate(), fine));
        tickets.add(new Ticket(vehicle.getPlate(), fine));
        System.out.println("Issuing ticket to: " + vehicle.toString() + " in spot " + spot + " for " + fine);
    }
    //Makes the officer patrol the lot
    void patrolLot(){

        for (int counter = 0; counter < lot.getCapacity(); counter ++){
            Vehicle v = lot.getSpot(counter).getVehicle();
            if (v != null){

                Fine f = getFineVehicleSpot(v, lot.getSpot(counter));
                if (f != null && f != Fine.NO_FINE){

                    issueTicket(v, counter, f);
                    RITParking.pause(1);
                }
            }
        }
    }
    //sets the parking bool
    void setParkingLot(ParkingLot lot){this.lot = lot;}

    static void	main(String[] args){

    }
}
