package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import model.Weather.FAAAirport;
import model.Weather.LocalAirport;
import ui.AFRSInterface;

/**
 * This class contains all airports and flights for our routes
 */
public class RouteMap {
    
    List<Airport> airports;
    List<Flight> flights;
    private HashMap<AFRSInterface, HashMap<String, Airport>> userMethods;
    private HashMap<String, LocalAirport> localAirports;
    private HashMap<String, FAAAirport> faaAirports;
    
    
    /**
     * Constructor
     */
    public RouteMap(HashMap<String, LocalAirport> localAirports, HashMap<String, FAAAirport> faaAirports) {
        airports = new ArrayList<>();
        flights = new ArrayList<>();
        this.localAirports = localAirports;
        this.faaAirports = faaAirports;
    }
    
    /**
     * Adds flights without checks to the route collection of flights
     */
    public void addFlight(Flight newFlight) {
        flights.add(newFlight);
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
     *
     * @param flightNumber The number of the flight
     * @return null if not found, other wise the Flight
     */
    public Flight getFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
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
    
    /**
     * gets flights FROM a single airport
     */
    public List<Flight> getFlightsFrom(Airport airport) {
        List<Flight> foundFlights = new ArrayList<>();
        
        // find flights
        flights.forEach(flight -> {
            if (flight.getOrigin() == airport) {
                foundFlights.add(flight);
            }
        });
        
        return foundFlights;
    }
    
    
    /**
     * gets flights TO a single airport
     */
    public List<Flight> getFlightsTo(Airport airport) {
        List<Flight> foundFlights = new ArrayList<>();
        
        // find flights
        flights.forEach(flight -> {
            if (flight.getDestination() == airport) {
                foundFlights.add(flight);
            }
        });
        
        return foundFlights;
    }
    
    /**
     * Sets the method for each user.
     *
     * @param ui
     * @param argumentArray
     */
    public void setMethod(ui.AFRSInterface ui, ArrayList<String> argumentArray) {
        if (argumentArray.get(1).equals("local")) {
            userMethods.put(ui, localAirports);
        } else if (argumentArray.get(1).equals("faa")) {
            userMethods.put(ui, faaAirports);
        } else {
            ui.printString("error, unknown information server");
        }
    }
}
