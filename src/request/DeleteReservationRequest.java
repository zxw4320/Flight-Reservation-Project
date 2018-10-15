package request;

import model.Airport;
import model.Reservation;
import model.ReservationCollection;
import ui.AFRSInterface;

import java.util.List;

/**
 * Deletes a reservation from a ReservationCollection
 */
public class DeleteReservationRequest implements Request {

    private String passengerName;
    private String originAirportCode;
    private String destinationAirportCode;
    private AFRSInterface ui;
    private ReservationCollection reservations;

    /**
     * Constructor
     */
    public DeleteReservationRequest(AFRSInterface ui, String passengerName, String originAirportCode,
                                    String destinationAirportCode, ReservationCollection reservations){
        this.ui = ui;
        this.passengerName = passengerName;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.reservations = reservations;
    }


    @Override
    public void execute() {

        Reservation thisReservation = findReservation();

        // catch error
        if (thisReservation == null){
            ui.printString("error,reservation not found");
        }
        // process deletion
        else {
            reservations.deleteReservation(thisReservation);
            ui.printString("delete,successful");
        }
    }

    /**
     * Finds a reservation in this classes reservation request based on this
     * class's passengerName, originAirportCode, and destinationAirportCode
     * @return Reservation for that passenger
     */
    private Reservation findReservation(){

        List<Reservation> passengersReservations = reservations.findReservation(passengerName);

        // loop through all reservations
        for(Reservation reservation : passengersReservations){

            // compare strings
            Airport origin = reservation.getOrigin();
            Airport destination = reservation.getDestination();
            boolean originMatch = origin.getAirportcode().equals(originAirportCode);
            boolean destinationMatch = destination.getAirportcode().equals(destinationAirportCode);

            // if match, return
            if (originMatch && destinationMatch)
                return reservation;

        }
        return null;
    }
}