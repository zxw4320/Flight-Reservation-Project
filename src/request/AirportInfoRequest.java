package request;

import itinerary.Airport;
import itinerary.RouteMap;
import ui.AFRSInterface;

public class AirportInfoRequest implements Request {

    private String airportCode;
    private RouteMap routeMap;
    private AFRSInterface ui;


    public AirportInfoRequest(AFRSInterface ui, RouteMap routeMap, String airportCode){
        this.airportCode = airportCode;
        this.routeMap = routeMap;
        this.ui = ui;
    }


    @Override
    public void execute() {
        Airport airport = routeMap.getAirport(airportCode);
        ui.printString( airport.toString() + " is currently "
                + airport.getWeather() +" with " +
                Integer.toString(airport.getDelaytime()) + " minute delays.");
    }
}
