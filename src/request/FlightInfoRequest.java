package request;

import model.Flight;
import model.Itinerary;
import model.ItineraryHistory;
import model.RouteMap;

import java.util.ArrayList;
import java.util.List;

import request.FlightOrders.FlightOrder;
import ui.AFRSInterface;

/**
 * The request to gather all itineraries between the given airports that also comply
 * with other arguments given. It is a Concrete Command in the Command pattern.
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
            // creates all itineraries that fit the query
            ArrayList<Itinerary> itineraries = generateItineraries(routeMap.getFlights());
            itineraries = sortOrder.sortOrder(itineraries);
            StringBuilder result = new StringBuilder();
            result.append("info,").append(itineraries.size());
            // gathers all of the valid itineraries
            for (int i = 1; i <= itineraries.size(); i++) {
                result.append("\n").append(i).append(",").append((itineraries.get(i - 1)).toString());
            }
            String fn = result.toString();
            ui.printString(fn);
            itineraryHistory.addItineraries(ui, itineraries);
        }
    }

    /**
     * Helper method to generate Itineraries that fit all of the query information
     *
     * @return all itineraries that fit all the query requirements
     */
    private ArrayList<Itinerary> generateItineraries(List<Flight> flights) {

        // parts of the request
        String origin = flightRequest.get(1);
        String destination = flightRequest.get(2);
        String numConnect = flightRequest.get(3);

        ArrayList<Itinerary> itineraries = new ArrayList<>();

        // puts all single flights into the list
        for (Flight flight : flights) {
            if (flight.getOrigin().getAirportcode().equals(origin) &&
                    flight.getDestination().getAirportcode().equals(destination)) {
                ArrayList<Flight> temp = new ArrayList<>();
                temp.add(flight);
                itineraries.add(new Itinerary(temp));
            }
        }

        // puts all itineraries with 1 connection into the list
        if (numConnect.equals("1") || numConnect.equals("2") || numConnect.equals("")) {
            // flights that start at the origin, but end in another airport
            ArrayList<Flight> origins = new ArrayList<>();

            // flights that end in the destination, but start at another airport
            ArrayList<Flight> dests = new ArrayList<>();

            // gather all flights that are not in the model that start or end in the proper place
            for (Flight flight : flights) {
                if (!flight.getOrigin().getAirportcode().equals(origin) ||
                        !flight.getDestination().getAirportcode().equals(destination)) {
                    if (flight.getOrigin().getAirportcode().equals(origin)) {
                        origins.add(flight);
                    }
                    if (flight.getDestination().getAirportcode().equals(destination)) {
                        dests.add(flight);
                    }
                }
            }

// TODO Combinations following delay and arrival time
            // create all itineraries that have one connection
            for (Flight org : origins) { // go through all the origin flights
                for (Flight dest : dests) { // go through all the destination flights
                    if (org.getDestination() == dest.getOrigin()) { // flights that connect at the same airport
                        ArrayList<Flight> temp = new ArrayList<>();
                        temp.add(org);
                        temp.add(dest);
                        itineraries.add(new Itinerary(temp));
                    }
                }
            }

            // create all itineraries that have 2 connecting flights
            if (numConnect.equals("2") || numConnect.equals("")) {
                for (Flight flight : flights) { // go through every flight
                    for (Flight org : origins) { // go through all the origin flights
                        for (Flight dest : dests) { // go through all the destination flights
                            if (org.getDestination() == flight.getOrigin() &&
                                    flight.getDestination() == dest.getOrigin()) { // flights that connect at the same airport
                                ArrayList<Flight> temp = new ArrayList<>();
                                temp.add(org);
                                temp.add(flight);
                                temp.add(dest);
                                itineraries.add(new Itinerary(temp));
                            }
                        }
                    }
                }
            }
        }

        // puts all itineraries with 2 connections into the list


        return itineraries;
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
}
