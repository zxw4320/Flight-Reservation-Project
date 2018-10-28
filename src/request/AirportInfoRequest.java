package request;

import model.Airport;
import model.AirportWeatherStorage;
import model.RouteMap;
import ui.AFRSInterface;

/**
 * Responsible for gathering info on airports and returning it to the user.
 */
public class AirportInfoRequest implements Request {
    
    private String airportCode;
    private RouteMap routeMap;
    private AFRSInterface ui;
    private AirportWeatherStorage airportWeatherStorage;
    
    /**
     * Constructor
     */
    public AirportInfoRequest(AFRSInterface ui, RouteMap routeMap, AirportWeatherStorage airportWeatherStorage,
        String airportCode) {
        this.airportCode = airportCode;
        this.routeMap = routeMap;
        this.ui = ui;
        this.airportWeatherStorage = airportWeatherStorage;
    }
    
    
    @Override
    public void execute() {
        Airport airport = routeMap.getAirport(airportCode);
        if (airport == null) { // check airport is valid
            ui.printString("error,unknown airport");
        } else {
            airport.setWeatherMethod(airportWeatherStorage.getWeatherMethod(ui, airportCode));
            ui.printString(airport.toString() + " is currently "
                + airport.getWeather() + " with " +
                Integer.toString(airport.getDelaytime()) + " minute delays.");
        }
    }
    
    @Override
    public boolean unexecute() {
        return false;
    }
}
