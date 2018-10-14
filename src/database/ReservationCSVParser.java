package database;

import itinerary.ReservationCollection;

public class ReservationCSVParser implements Reservationdb {
    public ReservationCollection generateReservationCollection();

    public ReservationCollection writeToDB();
}
