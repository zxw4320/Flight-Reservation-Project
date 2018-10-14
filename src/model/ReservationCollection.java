package model;

import java.util.ArrayList;
import java.util.List;

public class ReservationCollection {

    private List<Reservation> reservations;

    public ReservationCollection(){
        reservations = new ArrayList<>();
    }

    /**
     * Adds reservation to collection.
     * @param reservation Reservation to add
     */
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    /**
     * Removes a reservation from the collection.
     * @param reservation Reservation to remove
     */
    public void deleteReservation(Reservation reservation){
        reservations.remove(reservation);
    }

    /**
     * Finds all reservations that match the given client name.
     * @param passenger
     * @return
     */
    public List<Reservation> findReservation(String passenger){

        List<Reservation> foundReservations = new ArrayList<>();

        // search all reservations for substring
        reservations.forEach(reservation -> {
            if( reservation.getPassenger().equals(passenger) ){
                foundReservations.add(reservation);
            }
        });

        return foundReservations;

    }

}
