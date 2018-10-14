package itinerary;

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

    /**
     * Converts times to entirely minutes for comparison
     */
    private static int convertTime(String t1){
        int result;
        int split = t1.indexOf(":");
        int hours = Integer.parseInt(t1.substring(0, split));
        int minutes = Integer.parseInt(t1.substring(split + 1, t1.length() - 1));
        char ampm = t1.charAt(t1.length() - 1);
        if(ampm == 'p' && hours != 12){
            hours += 12;
        }
        else if(hours == 12 && ampm == 'a'){
            hours = 0;
        }
        result = hours * 60 + minutes;
        return result;
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

    /**
     * Gets the total time of a flight in minutes TODO possibly remove
     */
    public int getTotalTime(){
        return convertTime(arrivalTime) - convertTime(departureTime);
    }
}
