package itinerary;

import java.util.ArrayList;
import java.util.List;

public class ReservationCollection {

    List<Reservation> reservations;

    public ReservationCollection(){
        reservations = new ArrayList<>();
    }

    /**
     * Adds reservation to collection.
     * @param reservation
     */
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void deleteReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    public List<Reservation> findReservation(String passenger){

        List<Reservation> foundReservations = new ArrayList<>();

        // search all reservations for substring
        reservations.forEach(reservation -> {
            if( reservation.getPassenger() == passenger ){
                foundReservations.add(reservation);
            }
        });

        return foundReservations;

    }

}
