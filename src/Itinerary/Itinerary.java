package Itinerary;

import java.util.ArrayList;

public class Itinerary implements FlightInterface {
    // TODO: 10/11/18 documentaion 
    public ArrayList<FlightInterface> flights;
    public Itinerary(ArrayList<FlightInterface> fs){
        flights = fs;
    }

    @Override
    public int getAirfare() {
        int sum = 0;

        for (FlightInterface flight : flights) {

            sum += flight.getAirfare();
        }
        return sum;
    }

    @Override
    public String getArrivalTime() {
        return flights.get(flights.size()-1).getArrivalTime();
    }

    @Override
    public String getDepartureTime() {
        return flights.get(0).getDepartureTime();
    }

    @Override
    public String getOrigin() {
        return flights.get(0).getOrigin();
    }

    @Override
    public String getDestination() {
        return flights.get(flights.size()-1).getDestination();
    }

    @Override
    public String getFlightNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for(FlightInterface flight:flights){
            stringBuilder.append(flight.getFlightNumber()).append(",");
        }
        String fn = stringBuilder.toString();
        fn = fn.substring(0, fn.length() - 1);
        return fn;
    }
}
