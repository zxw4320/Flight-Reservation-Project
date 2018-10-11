package Itinerary;

import java.util.Comparator;

public class Flight implements FlightInterface {

    private int flightNumber;
    private int airfare;
    private String arrival;
    private String departure;
    private String arrivalTime;
    private String departureTime;


    public Flight(int flightNumber, int airfare, String arrival, String departure,
        String arrivalTime, String departureTime) {
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
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     * Compares Flights by arrival time
     */
    public static Comparator<Flight> arrivalTimeComparator =
        (Flight f1, Flight f2) ->(Integer.compare(convertTime(f1.getDepartureTime()),
            convertTime(f2.getDepartureTime())));

    /**
     * Compares Flights by arrival time
     */
    public static Comparator<Flight> departureTimeComparator =
        (Flight f1, Flight f2) ->(Integer.compare(convertTime(f1.getDepartureTime()),
            convertTime(f2.getDepartureTime())));


    /**
     * Compares Flights by arrival time
     */
    public static Comparator<Flight> airfareComparator =
        (Flight f1, Flight f2) -> (Integer.compare(f1.getAirfare(), f2.getAirfare()));

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
}
