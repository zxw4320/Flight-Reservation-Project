package request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Airport;
import model.Flight;
import model.Itinerary;
import model.ItineraryHistory;
import model.RouteMap;
import request.FlightOrders.FlightOrder;
import ui.AFRSInterface;

/**
 * The request to gather all itineraries between the given airports that also comply with other
 * arguments given. It is a Concrete Command in the Command pattern.
 */
public class FlightInfoRequest implements Request {
    
    private AFRSInterface ui;
    private RouteMap routeMap;
    private ArrayList<String> flightRequest;
    private FlightOrder sortOrder;
    private ItineraryHistory itineraryHistory;
    
    /**
     * Constructor
     */
    public FlightInfoRequest(AFRSInterface ui, RouteMap routeMap,
        ArrayList<String> flightRequest, FlightOrder sortOrder,
        ItineraryHistory itineraryHistory) {
        this.ui = ui;
        this.routeMap = routeMap;
        this.flightRequest = flightRequest;
        this.sortOrder = sortOrder;
        this.itineraryHistory = itineraryHistory;
    }
    
    @Override
    public void execute() {
        if (checkArgumentsValid()) {
            
            // parts of the request
            String origin = flightRequest.get(1);
            String destination = flightRequest.get(2);
            String numConnect = flightRequest.get(3);
    
            if (numConnect.equals("")) {
                numConnect = "2";
            }
            
            // creates all itineraries that fit the query
            ArrayList<Itinerary> itineraries = findFlights(routeMap.getAirport(origin),
                routeMap.getAirport(destination), Integer.parseInt(numConnect));
            itineraries = sortOrder.sortOrder(itineraries);
            StringBuilder result = new StringBuilder();
            result.append("info,").append(itineraries.size());
            
            // gathers all of the valid itineraries
            for (int i = 1; i <= itineraries.size(); i++) {
                ui.printString(i + "," + (itineraries.get(i - 1)).toString());
            }
            String fn = result.toString();
            ui.printString(fn);
            itineraryHistory.addItineraries(ui, itineraries);
        }
    }
    
    /**
     * Checks that all of the arguments passed to the Request were valid.
     *
     * @return true if valid, false otherwise
     */
    private boolean checkArgumentsValid() {
        // parts of the query that should always be present
        String origin = flightRequest.get(1);
        String destination = flightRequest.get(2);
        String numConnect = flightRequest.get(3);
        
        // check origin is valid
        if (routeMap.getAirport(origin) == null) {
            ui.printString("error,unknown origin");
            return false;
            
            // check destination is valid
        } else if (routeMap.getAirport(destination) == null) {
            ui.printString("error,unknown destination");
            return false;
            
            // check connection number is valid
        } else if (!numConnect.equals("0") && !numConnect.equals("1") &&
            !numConnect.equals("2") && !numConnect.equals("")) {
            ui.printString("error,invalid connection limit");
            return false;
        }
        return true;
    }
    
    
    private ArrayList<Itinerary> findFlights(Airport origin, Airport destination,
        int depth) {
        // generate itinerary list
        List<Flight> flights = new ArrayList<>(routeMap.getFlightsFrom(origin));
        List<Itinerary> itineraries = new ArrayList<>();
        flights.forEach(flight -> itineraries.add(new Itinerary(flight)));
        
        // find direct flights
        flights.retainAll(routeMap.getFlightsTo(destination));
        // convert to itineraries
        ArrayList<Itinerary> completeItineraries = new ArrayList<>();
        flights.forEach(flight -> completeItineraries.add(new Itinerary(flight)));
        
        // start recursion
        if (depth > 0) {
            completeItineraries.addAll(findFlightHelper(itineraries, destination, depth));
        }
        return completeItineraries;
    }
    
    
    private ArrayList<Itinerary> findFlightHelper(List<Itinerary> itineraryList,
        Airport destination, int depth) {
        
        ArrayList<Itinerary> completeItinerary = new ArrayList<>();
        ArrayList<Itinerary> partialItinerary = new ArrayList<>();
        depth--;
        
        // set for airports
        Set<Airport> interimAirports = new HashSet<Airport>();
        // find all airports we need
        itineraryList.forEach(flightInterface ->
            interimAirports.add(flightInterface.getDestination()));
        
        // get the flights to from each airport we have
        HashMap<Airport, List<Flight>> flightsFrom = new HashMap<>();
        interimAirports.forEach(airport -> {
            flightsFrom.put(airport, routeMap.getFlightsFrom(airport));
        });
        
        // iterate over itineraries from input
        itineraryList.forEach(itinerary -> {
            Airport currentDestination = itinerary.getDestination();
            // iterate over flights from current location
            flightsFrom.get(currentDestination).forEach(flight -> {
                // if we have a valid flight for current itinerary we add it
                if (fitsItinerary(flight, itinerary)) {
                    List<Flight> newItineraryFlights = itinerary.getFlights();
                    newItineraryFlights.add(flight);
                    partialItinerary.add(new Itinerary(newItineraryFlights));
                }
            });
        });
        
        // get destination flights
        // remove from partial flights list
        partialItinerary.forEach(itinerary -> {
            if (itinerary.getDestination() == destination) {
                completeItinerary.add(itinerary);
            }
        });
        partialItinerary.removeAll(completeItinerary);
        
        // Recurse! and Return!
        if (depth > 0) {
            completeItinerary.addAll(findFlightHelper(partialItinerary, destination, depth));
        }
        return completeItinerary;
    }
    
    private boolean fitsItinerary(Flight flight, Itinerary itinerary) {
        int arriveAtAirport = itinerary.getRawDelayedArrivalTime();
        int leaveAirport = flight.getRawDepartureTime();
        boolean flightReachable = arriveAtAirport < leaveAirport;
        return flightReachable;
    }
    
    @Override
    public boolean unexecute() {
        return false;
    }
}
