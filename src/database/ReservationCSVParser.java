package database;

import itinerary.ReservationCollection;

public class ReservationCSVParser implements Reservationdb {
    private ReservationCollection reservations = new ReservationCollection();

    public ReservationCollection generateReservationCollection(){

        return reservations;
    }
    //TODO Read Reservations From CSV
    public void writeToDB(){

    }
    //TODO Write Reservations To CSV
}
