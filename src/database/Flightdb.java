package database;

import model.AirportWeatherStorage;
import model.RouteMap;

/**
 * Interface for a database of flights
 */
public interface Flightdb {

    /**
     * Generates a routemap from DB contents
     *
     * @return RouteMap
     */
    RouteMap generateRouteMap();
    
    /**
     * Generates an airportStorage from the DB contents
     *
     * @return AirportStorge with local and FAA weather objects
     */
    AirportWeatherStorage generateAirportWeatherStorage();
}
