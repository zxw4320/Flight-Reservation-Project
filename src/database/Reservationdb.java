package database;

import itinerary.Reservation;
import itinerary.ReservationCollection;

public interface Reservationdb {

    public ReservationCollection generateReservationCollection();

    public Void writeToDB(ReservationCollection reservationCollection);

}
