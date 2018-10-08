package Itinerary;

import java.util.ArrayList;

public class Itinerary implements FlightInterface {

    public ArrayList<FlightInterface> flights;
    public Itinerary(ArrayList<FlightInterface> fs){
        flights = fs;
    }

    @Override
    public int getAirfare() {
        return 0;
    }

    @Override
    public String getArrivalTime() {
        return null;
    }
//
    @Override
    public String getDepartureTime() {
        return null;
    }

    @Override
    public String getOrigin() {
        return null;
    }

    @Override
    public String getDestination() {
        return null;
    }

    @Override
    public int getFlightNumber() {
        return 0;
    }
}
