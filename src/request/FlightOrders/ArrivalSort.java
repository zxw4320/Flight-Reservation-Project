package request.FlightOrders;

import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts all flights by their time of arrival.
 * This is a concrete strategy in the Strategy design pattern.
 */
public class ArrivalSort implements FlightOrder{

  /**
   * Orders and returns all itineraries based on arrival time.
   *
   * @return the list of itineraries sorted based on arrival time
   */
  public ArrayList<Itinerary> sortOrder(ArrayList<Itinerary> itineraries){
    Collections.sort(itineraries ,Itinerary.arrivalTimeComparator);
    return itineraries;
  }
}
