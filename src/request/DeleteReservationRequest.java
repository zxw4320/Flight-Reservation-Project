package request;

import java.util.List;
import model.Airport;
import model.Reservation;
import model.ReservationCollection;
import ui.AFRSInterface;

/**
 * Deletes a reservation from a ReservationCollection
 */
public class DeleteReservationRequest implements Request {
    
    private String passengerName;
    private String originAirportCode;
    private String destinationAirportCode;
    private AFRSInterface ui;
    private ReservationCollection reservations;
    private Reservation reservation;
    private final String name = "delete";

    /**
     * Constructor
     */
    public DeleteReservationRequest(AFRSInterface ui, String passengerName,
        String originAirportCode,
        String destinationAirportCode, ReservationCollection reservations) {
        this.ui = ui;
        this.passengerName = passengerName;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.reservations = reservations;
        this.reservation = null;
    }
    
    
    @Override
    public void execute() {
        
        reservation = findReservation();
        
        // catch error
        if (reservation == null) {
            ui.printString("error,reservation not found");
        }
        // process deletion
        else {
            reservations.deleteReservation(reservation);
            ui.printString("delete,successful");
        }
    }
    
    /**
     * Finds a reservation in this classes reservation request based on this class's passengerName,
     * originAirportCode, and destinationAirportCode
     *
     * @return Reservation for that passenger
     */
    private Reservation findReservation() {
        
        List<Reservation> passengersReservations = reservations.findReservation(passengerName);
        
        // loop through all reservations
        for (Reservation reservation : passengersReservations) {
            
            // compare strings
            Airport origin = reservation.getOrigin();
            Airport destination = reservation.getDestination();
            boolean originMatch = origin.getAirportcode().equals(originAirportCode);
            boolean destinationMatch = destination.getAirportcode().equals(destinationAirportCode);
            
            // if match, return
            if (originMatch && destinationMatch) {
                return reservation;
            }
            
        }
        return null;
    }
    
    @Override
    public boolean unexecute() {
        // if we have a reservation, we can undo this command
        if (reservation != null) {
            reservations.addReservation(reservation);
            return true;
        }
        // return that we did nothing
        return false;
    }
}
