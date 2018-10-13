package Itinerary;

public class Flight implements FlightInterface {
    private String flightNumber;
    private int airfare;
    private String arrival;
    private String departure;
    private String arrivalTime;
    private String departureTime;

    // TODO: 10/11/18 documentation 
    public Flight(String flightNumber, int airfare, String arrival, String departure, String arrivalTime, String departureTime) {
        this.flightNumber = flightNumber;
        this.airfare = airfare;
        this.arrival = arrival;
        this.departure = departure;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
//
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
    public String getOrigin() {
        return departure;
    }

    @Override
    public String getDestination() {
        return arrival;
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }
}
