package Itinerary;

import java.util.ArrayList;
import java.util.Comparator;

public class Itinerary implements FlightInterface {

    private ArrayList<FlightInterface> flights;

    public Itinerary(ArrayList<FlightInterface> fs){
        flights = fs;
    }

    @Override
    public int getAirfare() {
        return 0;
    }

    @Override
    public String getArrivalTime() {
        return flights.get(flights.size() - 1).getArrivalTime();
    }
//
    @Override
    public String getDepartureTime() {
        return flights.get(0).getDepartureTime();
    }

    @Override
    public String getOrigin() {
        return flights.get(0).getOrigin();
    }

    @Override
    public String getDestination() {
        return flights.get(flights.size() - 1).getDestination();
    }

    @Override
    public int getFlightNumber() {
        return 0;
    }

    /**
     * Compares Flights by arrival time
     */
    public static Comparator<Itinerary> arrivalTimeComparator =
        (Itinerary i1, Itinerary i2) ->(Integer.compare(convertTime(i1.getArrivalTime()),
            convertTime(i2.getArrivalTime())));

    /**
     * Compares Flights by arrival time
     */
    public static Comparator<Itinerary> departureTimeComparator =
        (Itinerary i1, Itinerary i2) ->(Integer.compare(convertTime(i1.getDepartureTime()),
            convertTime(i2.getDepartureTime())));


    /**
     * Compares Flights by arrival time
     */
    public static Comparator<Itinerary> airfareComparator =
        (Itinerary i1, Itinerary i2) -> (Integer.compare(i1.getAirfare(), i2.getAirfare()));

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
