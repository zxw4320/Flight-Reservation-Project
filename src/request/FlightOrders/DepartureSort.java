package request.FlightOrders;

import itinerary.Flight;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts all flights by their time of departure.
 * This is a concrete strategy in the Strategy design pattern.
 */
public class DepartureSort implements FlightOrder {

  /**
   * Orders and returns all flights based on departure time.
   */
  public ArrayList<Flight> sortOrder(ArrayList<Flight> flights){
    Collections.sort(flights, Flight.departureTimeComparator);
    return flights;
  }
}
