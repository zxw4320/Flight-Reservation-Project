package request.FlightOrders;

import itinerary.Flight;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts all flights by the cost of airfare.
 * This is a concrete strategy in the Strategy design pattern.
 */
public class AirfareSort implements FlightOrder {

  /**
   * Orders and returns all flights based on the cost of airfare.
   */
  public ArrayList<Flight> sortOrder(ArrayList<Flight> flights){
    Collections.sort(flights, Flight.airfareComparator);
    return flights;
  }
}
