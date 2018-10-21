package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.toIntExact;

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

    private int timeToInt(String time){
        SimpleDateFormat ft = new SimpleDateFormat("h:mma");
        try {
            Date parsedTime = ft.parse(time.replace("a", "am").replace("p", "pm"));
            long secondsSince1970 = parsedTime.getTime();
            int minutesSince1970 = toIntExact(secondsSince1970/60);
            return minutesSince1970;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String toString() {
        return getFlightNumber() + "," + getOrigin().getAirportcode() + "," +
            getDepartureTime() + "," + getDestination().getAirportcode() + "," +
            getArrivalTime();
    }

    /*** Getters ***/


    @Override
    public int getRawDelayedArrivalTime() {
        int delay = origin.getDelaytime();
        int arrival = timeToInt(arrivalTime);
        return arrival + delay;
    }

    @Override
    public int getRawDepartureTime() {
        return timeToInt(departureTime);
    }

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
