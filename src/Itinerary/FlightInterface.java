package Itinerary;

/**
 *
 */
public interface FlightInterface {
    int getAirfare();

    String getArrivalTime();

    String getDepartureTime();

    Airport getOrigin();

    Airport getDestination();

    String getFlightNumber();
}
//