package database;

import model.RouteMap;

public interface Flightdb {

    /**
     * Generates a routemap from DB contents
     * @return RouteMap
     */
    public RouteMap generateRouteMap();
}
