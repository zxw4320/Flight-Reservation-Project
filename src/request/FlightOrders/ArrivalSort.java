package request.FlightOrders;

import Itinerary.Flight;
import java.util.ArrayList;

/**
 * Sorts all flights by their time of arrival.
 * This is a concrete strategy in the Strategy design pattern.
 */
public class ArrivalSort implements FlightOrder{

  /**
   * Orders and returns all flights based on arrival time.
   */
  public ArrayList<Flight> sortOrder(ArrayList<Flight> flights){
    return null;
  }
}
