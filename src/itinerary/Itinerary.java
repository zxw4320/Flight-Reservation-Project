package itinerary;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * By Tyler Baldwin
 */
public class Itinerary implements FlightInterface {

    private ArrayList<FlightInterface> flights;

    public Itinerary(ArrayList<FlightInterface> fs){
        flights = fs;
    }

    /**
     * Compares itinerary by arrival time
     */
    public static Comparator<Itinerary> arrivalTimeComparator =
        (Itinerary i1, Itinerary i2) ->(Integer.compare(convertTime(i1.getArrivalTime()),
            convertTime(i2.getArrivalTime())));

    /**
     * Compares itinerary by arrival time
     */
    public static Comparator<Itinerary> departureTimeComparator =
        (Itinerary i1, Itinerary i2) ->(Integer.compare(convertTime(i1.getDepartureTime()),
            convertTime(i2.getDepartureTime())));


    /**
     * Compares itinerary by arrival time
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getAirfare()).append(",").append(flights.size());
        for(FlightInterface flight : flights){
            result.append(",").append(flight.toString());
        }
        String fn = result.toString();
        fn = fn.substring(0, fn.length() - 1);
        return fn;
    }

    /*** getters ***/

    @Override
    public int getAirfare() {
        int sum = 0;

        for (FlightInterface flight : flights) {

            sum += flight.getAirfare();
        }
        return sum;
    }

    @Override
    public String getArrivalTime() {
        return flights.get(flights.size() - 1).getArrivalTime();
    }

    @Override
    public String getDepartureTime() {
        return flights.get(0).getDepartureTime();
    }

    @Override
    public Airport getOrigin() {
        return flights.get(0).getOrigin();
    }

    @Override
    public Airport getDestination() {
        return flights.get(flights.size() - 1).getDestination();
    }

    @Override
    public String getFlightNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for(FlightInterface flight:flights){
            stringBuilder.append(flight.getFlightNumber()).append(",");
        }
        String fn = stringBuilder.toString();
        fn = fn.substring(0, fn.length() - 1);
        return fn;
    }
}
