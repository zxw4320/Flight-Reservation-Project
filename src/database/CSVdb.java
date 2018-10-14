package database;

import itinerary.Airport;
import itinerary.Flight;
import itinerary.RouteMap;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class CSVdb implements Flightdb {

    Path airportFile;
    Path weatherFile;
    Path flightFile;
    Path delayFile;

    public CSVdb(Path airportFile, Path weatherFile, Path flightFile, Path delayFile){
        this.airportFile = airportFile;
        this.weatherFile = weatherFile;
        this.flightFile = flightFile;
        this.delayFile = delayFile;
    }

    @Override
    public RouteMap generateRouteMap() {

        // variables for data
        HashMap<String, String[]> weatherMap = new HashMap<>();
        HashMap<String, Integer> delayMap = new HashMap<>();
        RouteMap routeMap = new RouteMap();

        // read in weather data for airports that we have on file
        for (String[] sArray : parseLinesInFile(weatherFile)) {
            weatherMap.put(sArray[0], sArray);
        }

        // read in delay data for airports that we have on file
        for (String[] sArray : parseLinesInFile(delayFile)) {
            delayMap.put(sArray[0], Integer.parseInt(sArray[1]));
        }

        // read airportFile and add data to routeMap
        for (String[] sArray : parseLinesInFile(airportFile)){
                String airportCode = sArray[0];
                String cityName = sArray[1];
                int delaytime = delayMap.get(airportCode);
                String[] weather = weatherMap.get(airportCode);
                routeMap.addAirport(new Airport(airportCode, cityName,
                        delaytime, weather));
       }

        // read flight data and add to routeMap
        for (String[] sArray : parseLinesInFile(flightFile)) {
            Airport origin = routeMap.getAirport(sArray[0]);
            Airport destination = routeMap.getAirport(sArray[1]);
            String departureTime = sArray[2];
            String arrivalTime = sArray[3];
            String flightNumber = sArray[4];
            int airfare = Integer.parseInt(sArray[5]);
            routeMap.addFlight(new Flight(flightNumber, airfare, origin,
                    destination, arrivalTime, departureTime));
        }

        return routeMap;
    }

    /**
     * A simple method to parse the csv file
     * @param file
     * @return
     */
    private List<String[]> parseLinesInFile(Path file){

        List<String[]> returnList = new ArrayList<>();

        // parse the file from the path
        try (Stream<String> stream = Files.lines(file)) {
            stream.forEach(s -> {
                // parsing each line
                String[] sArray = s.split(",");
                returnList.add(sArray);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnList;
    }

}
