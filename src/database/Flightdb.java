package database;

import model.RouteMap;

/**
 * Interface for all
 */
public interface Flightdb {

    /**
     * Generates a routemap from DB contents
     * @return RouteMap
     */
    public RouteMap generateRouteMap();
}
