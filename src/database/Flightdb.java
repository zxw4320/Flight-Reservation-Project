package database;

import itinerary.RouteMap;

public interface Flightdb {

    /**
     * Generates a routemap from DB contents
     * @return RouteMap
     */
    public RouteMap generateRouteMap();
}
