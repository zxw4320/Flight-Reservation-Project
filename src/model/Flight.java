package model;

/**
 *  An implementation of the FlightInterface for a simple flight. Represents a leaf in
 *  the Composite pattern.
 */
public class Flight implements FlightInterface {

    private String flightNumber;
    private int airfare;
    private Airport origin;
    private Airport destination;
    private String arrivalTime;
    private String departureTime;

    /**
     * Constructor
     */
    public Flight(String flightNumber, int airfare, Airport origin, Airport destination, String arrivalTime, String departureTime) {
        this.flightNumber = flightNumber;
        this.airfare = airfare;
        this.origin = origin;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return getFlightNumber() + "," + getOrigin().getAirportcode() + "," +
            getDepartureTime() + "," + getDestination().getAirportcode() + "," +
            getArrivalTime();
    }

    /*** Getters ***/

    @Override
    public int getAirfare() {
        return airfare;
    }

    @Override
    public String getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String getDepartureTime() {
        return departureTime;
    }

    @Override
    public Airport getOrigin() {
        return origin;
    }

    @Override
    public Airport getDestination() {
        return destination;
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }
}
