package request.FlightOrders;

import model.Itinerary;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts all flights by the cost of airfare.
 * This is a concrete strategy in the Strategy design pattern.
 */
public class AirfareSort implements FlightOrder {

  /**
   * Orders and returns all itineraries based on the cost of airfare.
   *
   * @return the lsit of itineraries sorted based on the cost of airfare
   */
  public ArrayList<Itinerary> sortOrder(ArrayList<Itinerary> itineraries){
    Collections.sort(itineraries ,Itinerary.airfareComparator);
    return itineraries;
  }
}
