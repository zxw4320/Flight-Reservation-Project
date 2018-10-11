package request.FlightOrders;

import Itinerary.Flight;
import java.util.ArrayList;

/**
 * Sorts all flights by the cost of airfare.
 * This is a concrete strategy in the Strategy design pattern.
 */
public class AirfareSort implements FlightOrder {

  /**
   * Orders and returns all flights based on the cost of airfare.
   */
  public ArrayList<Flight> sortOrder(ArrayList<Flight> flights){
    return null;
  }
}
