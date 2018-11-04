package request;

import java.util.List;
import model.Itinerary;
import model.ItineraryHistory;
import model.Reservation;
import model.ReservationCollection;
import ui.AFRSInterface;

/**
 * Makes a reservation request and adds to the given ReservationCollection
 */
public class MakeReservationRequest implements Request {
    
    private int id;
    private String passenger;
    private AFRSInterface ui;
    private ReservationCollection reservationCollection;
    private ItineraryHistory itineraryHistory;
    private Reservation reservation;
    
    /**
     * Constructor
     */
    public MakeReservationRequest(AFRSInterface ui, ReservationCollection reservationCollection,
        ItineraryHistory itineraryHistory,
        int id, String passenger) {
        this.ui = ui;
        this.reservationCollection = reservationCollection;
        this.itineraryHistory = itineraryHistory;
        this.id = id;
        this.passenger = passenger;
        this.reservation = null;
    }
    
    @Override
    public void execute() {
        // get itinerary
        Itinerary itinerary = itineraryHistory.getItinerary(ui, id);
        
        // catch fail to find itinerary
        if (itinerary == null) {
            ui.printString("error,invalid id");
            return;
        }
        
        // catch duplicate reservation
        List<Reservation> reservationList = reservationCollection.findReservation(passenger);
        // for each reservation with this passenger
        for (Reservation reservation : reservationList) {
            // check for duplicate origin/destination
            boolean originMatch = reservation.getOrigin() == itinerary.getOrigin();
            boolean destinationMatch = reservation.getDestination() == itinerary.getDestination();
            // handle full duplicate with error
            if (destinationMatch && originMatch) {
                ui.printString("error,duplicate reservation");
                return;
            }
        }
        
        // make reservation, no errors caught
        ui.printString("reserve,successful");
        reservation = new Reservation(passenger, itinerary);
        reservationCollection.addReservation(reservation);
        
    }
    
    @Override
    public boolean unexecute() {
        // check for reservation
        if (reservation != null) {
            reservationCollection.deleteReservation(reservation);
            return true;
        }
        // handle unable to unexecute
        return false;
    }
}
