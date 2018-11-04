package database;

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
}
