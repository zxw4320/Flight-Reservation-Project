package model;

import database.Reservationdb;

import java.util.*;

/**
 * Acts to collect all reservations currently stored in the system
 */
public class ReservationCollection {

    private List<Reservation> reservations;
    private Reservationdb db;

    /**
     * Constructor
     */
    public ReservationCollection(Reservationdb db, List<Reservation>
            reservations){
        this.db = db;
        this.reservations = new ArrayList<>(reservations);
    }

    /**
     * Adds reservation to collection.
     * @param reservation Reservation to add
     */
    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        write();
    }

    /**
     * Removes a reservation from the collection.
     * @param reservation Reservation to remove
     */
    public void deleteReservation(Reservation reservation){
        reservations.remove(reservation);
        write();
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

    /**
     * Finds all reservations.
     * @return an iterative list of all reservations.
     */
    public List<Reservation> listReservations(){
        ArrayList<Reservation> resCopy = new ArrayList<>(reservations);
        return resCopy;
    }

    /**
     * Writes self to db
     */
    private void write(){
        try {
            db.writeToDB(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
