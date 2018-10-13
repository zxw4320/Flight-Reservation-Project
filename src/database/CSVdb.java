package database;

import Itinerary.Airport;
import Itinerary.Flight;
import Itinerary.RouteMap;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class CSVdb implements Flightdb {

    Path airportFile;
    Path weatherFile;
    Path flightFile;

    public CSVdb(Path airportFile, Path weatherFile, Path flightFile){
        this.airportFile = airportFile;
        this.weatherFile = weatherFile;
        this.flightFile = flightFile;
    }

    @Override
    public RouteMap generateRouteMap() {

        // variables for data
        HashMap<String, String[]> weatherMap = new HashMap<>();
        RouteMap routeMap = new RouteMap();

        // read in weather data for airports that we have on file
        try (Stream<String> stream = Files.lines(airportFile)) {
            stream.forEach(s -> {
                // parsing each line
                String[] sArray = s.split(",");
                weatherMap.put(sArray[0], sArray);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // read airportFile and add data to routeMap
        try (Stream<String> stream = Files.lines(weatherFile)) {
            stream.forEach(s -> {
                // parsing each line
                String[] sArray = s.split(",");
                String airportCode = sArray[0];
                String cityName = sArray[1];
                String[] weather = weatherMap.get(airportCode);
                routeMap.addAirport(new Airport(airportCode, cityName,
                        weather));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // read flight data and add to routeMap
        try (Stream<String> stream = Files.lines(flightFile)) {
            stream.forEach(s -> {
                // parsing each line
                String[] sArray = s.split(",");
                Airport origin = routeMap.getAirport(sArray[0]);
                Airport destination = routeMap.getAirport(sArray[1]);
                String departureTime = sArray[2];
                String arrivalTime = sArray[3];
                String flightNumber = sArray[4];
                int airfare = Integer.parseInt(sArray[5]);
                routeMap.addFlight(new Flight(flightNumber, airfare, origin,
                        destination, arrivalTime, departureTime));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return routeMap;


    }
}
