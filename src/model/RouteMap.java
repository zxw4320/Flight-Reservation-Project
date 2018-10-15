package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all airports and flights for our routes
 */
public class RouteMap {

  List<Airport> airports;
  List<Flight> flights;

  /**
   * Constructor
   */
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

  /**
   * Gets a flight from a flight number
   * @param flightNumber The number of the flight
   * @return null if not found, other wise the Flight
   */
  public Flight getFlight(String flightNumber) {
    for(Flight flight : flights){
      if(flight.getFlightNumber().equals(flightNumber))
        return flight;
    }
    return null;
  }
  
   /**
   * Returns all the flights for the system
   *
   * @return all the flights in the system
   */
  public List<Flight> getFlights() {
    return flights;
  }
}