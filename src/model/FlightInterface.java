package model;

/**
 *  This class sets up the behavior of flights and interfaces
 *  allowing them to be used interchangeably.
 */
public interface FlightInterface {

    int getAirfare();

    int getRawDelayedArrivalTime();

    int getRawDepartureTime();

    String getArrivalTime();

    String getDepartureTime();

    Airport getOrigin();

    Airport getDestination();

    String getFlightNumber();

    String toString();
}