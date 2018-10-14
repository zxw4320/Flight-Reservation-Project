package request;

import itinerary.Reservation;
import itinerary.ReservationCollection;

import java.util.List;

public class DeleteReservationRequest implements Request {

    private String passengerName;
    private String originAirportCode;
    private String destinationAirportCode;
    private ReservationCollection reservations;


    public DeleteReservationRequest(String passengerName, String originAirportCode,
                             String destinationAirportCode, ReservationCollection reservations){
        this.passengerName = passengerName;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.reservations = reservations;
    }


    @Override
    public void execute() {
        if (this.findReservation() == null){
            System.out.print("error,reservation not found");
        }
        else{
            reservations.deleteReservation(this.findReservation());
            System.out.print("delete,successful");
        }
    }

    private Reservation findReservation(){
        Reservation thisReservation = null;
        List<Reservation> passengersReservations = reservations.findReservation(passengerName);
        for(Reservation reservation : passengersReservations){
            if ((reservation.getOrigin().getAirportcode().equals(originAirportCode)) &&
                    (reservation.getDestination().getAirportcode().equals(originAirportCode))){
                thisReservation = reservation;
            }
        }
        return thisReservation;
    }
}
