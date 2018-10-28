package request.FlightOrders;

import java.util.ArrayList;
import java.util.Collections;
import model.Itinerary;

/**
 * Sorts all flights by their time of departure. This is a concrete strategy in the Strategy design
 * pattern.
 */
public class DepartureSort implements FlightOrder {
    
    /**
     * Orders and returns all itineraries based on departure time.
     *
     * @return the list of itineraries sorted based on departure time
     */
    public ArrayList<Itinerary> sortOrder(ArrayList<Itinerary> itineraries) {
        Collections.sort(itineraries, Itinerary.departureTimeComparator);
        return itineraries;
    }
}
