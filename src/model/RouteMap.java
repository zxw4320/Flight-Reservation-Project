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
    
    List<Flight> flights;
    private HashMap<AFRSInterface, HashMap<String, Airport>> userMethods;
    private HashMap<String, Airport> localAirports;
    private HashMap<String, Airport> faaAirports;
    
    
    /**
     * Constructor
     */
    public RouteMap(HashMap<String, Airport> localAirports, HashMap<String, Airport> faaAirports) {
        flights = new ArrayList<>();
        this.localAirports = localAirports;
        this.faaAirports = faaAirports;
        this.userMethods = new HashMap<>();
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
    public Airport getAirport(AFRSInterface ui, String airportCode) {
        return userMethods.get(ui).get(airportCode);
    }
    
    public Airport getAirport(String airportCode){
        return localAirports.get(airportCode);
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
    public List<Flight> getFlightsFrom(String airportCode) {
        List<Flight> foundFlights = new ArrayList<>();
        
        // find flights
        flights.forEach(flight -> {
            if (flight.getOrigin().getAirportcode().equals(airportCode)) {
                foundFlights.add(flight);
            }
        });
        
        return foundFlights;
    }
    
    
    /**
     * gets flights TO a single airport
     */
    public List<Flight> getFlightsTo(String airportCode) {
        List<Flight> foundFlights = new ArrayList<>();
        
        // find flights
        flights.forEach(flight -> {
            if (flight.getDestination().getAirportcode().equals(airportCode)) {
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
            ui.printString("local,successful");
        } else if (argumentArray.get(1).equals("faa")) {
            userMethods.put(ui, faaAirports);
            ui.printString("faa,successful");
        } else {
            ui.printString("error, unknown information server");
        }
    }
}
