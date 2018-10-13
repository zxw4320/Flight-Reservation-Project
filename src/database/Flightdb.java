package database;

import Itinerary.RouteMap;

public interface Flightdb {

    /**
     * Generates a routemap from DB contents
     * @return RouteMap
     */
    public RouteMap generateRouteMap();
}
