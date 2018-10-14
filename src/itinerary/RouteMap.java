package itinerary;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all airports and flights for our routes
 */
public class RouteMap {

  List<Airport> airports;
  List<Flight> flights;

  public RouteMap() {
    airports = new ArrayList<>();
    flights = new ArrayList<>();
  }

  /**
   * Adds flights without checks to the route collection of flights
   */
  public void addFlight(Flight newFlight) {
    flights.add(newFlight);
  }

  /**
   * Adds airports without checks to the route collection of flights
   */
  public void addAirport(Airport newAirport) {
    airports.add(newAirport);
  }

  /**
   * Finds the airport for a given airport code string.
   *
   * @param airportCode String version of airport code
   * @return Airport or null if airport not found
   */
  public Airport getAirport(String airportCode) {
    for (Airport airport : airports) {
      if (airport.getAirportcode().equals(airportCode)) {
        return airport;
      }
    }
    return null;
  }


  public List<Flight> getFlights() {
    return flights;
  }
}
