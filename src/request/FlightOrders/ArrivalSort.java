package request.FlightOrders;

import itinerary.Flight;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts all flights by their time of arrival.
 * This is a concrete strategy in the Strategy design pattern.
 */
public class ArrivalSort implements FlightOrder{

  /**
   * Orders and returns all flights based on arrival time.
   */
  public ArrayList<Flight> sortOrder(ArrayList<Flight> flights){
    Collections.sort(flights, Flight.arrivalTimeComparator);
    return flights;
  }
}
