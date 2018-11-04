package request.FlightOrders;

import java.util.ArrayList;
import model.Itinerary;

/**
 * This is the interface that sets up all the ways a flight can be returned to the user. It is the
 * strategy in the Strategy design pattern.
 */
public interface FlightOrder {
    
    /**
     * Orders and returns all flights.
     */
    ArrayList<Itinerary> sortOrder(ArrayList<Itinerary> itineraries);
}
