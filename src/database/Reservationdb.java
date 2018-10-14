package database;

import itinerary.Reservation;
import itinerary.ReservationCollection;

public interface Reservationdb {

    public ReservationCollection generateReservationCollection();
    //TODO Read Reservations From CSV
    public void writeToDB();
    //TODO Write Reservations To CSV
}
