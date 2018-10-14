package database;

import model.ReservationCollection;
import model.RouteMap;

/**
 * This is the interface for the system that parses the CSV file holding the reservations from the previous session.
 */
public interface Reservationdb {
    /**
     * Parse CSV to a collection of reservations.
     * @param routeMap the map of all live system data.
     */
    ReservationCollection generateReservationCollection(RouteMap routeMap);

    /**
     * Write collection of reservations to CSV.
     * @param reservationCollection a collection of all active reservations.
     */
    void writeToDB(ReservationCollection reservationCollection);
}
