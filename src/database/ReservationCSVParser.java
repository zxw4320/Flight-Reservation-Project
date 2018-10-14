package database;

import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class that parses and writes to a CSV file for the reservations to persist.
 */
public class ReservationCSVParser implements Reservationdb {

    Path file;
    RouteMap routeMap;

    /**
     * Constructor.
     * @param file a path to a csv file of reservations.
     */
    public ReservationCSVParser(Path file){
        this.file = file;
    }


    public ReservationCollection generateReservationCollection(RouteMap routeMap){
        ReservationCollection reservations = new ReservationCollection();
        List<String[]> returnList = new ArrayList<>();

        // parse the file from the path to an array of string arrays
        try (Stream<String> stream = Files.lines(file)) {
            stream.forEach(s -> {
                // parsing each line
                String[] sArray = s.split(",");
                returnList.add(sArray);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // parses the array to a collection of reservations
        for(String[] line:returnList){
            String name = line[0]; //The first element of the csv line should be the passenger name
            ArrayList<Flight> trip = new ArrayList<Flight>();
            for(int i = 1;i<line.length;i++){
                Flight flight = routeMap.getFlight(line[i]);
                trip.add(flight);
            }
            Itinerary itinerary = new Itinerary(trip);
            Reservation reservation = new Reservation(name,itinerary);
            reservations.addReservation(reservation);
        }

        return reservations;
    }

    public void writeToDB(ReservationCollection reservations) throws IOException {
        String name; // The passenger's name
        String line; // The line to be written to the csv
        ArrayList<String> lines = new ArrayList<>();
        for(Reservation reservation:reservations.listReservations()){
            name = reservation.getPassenger();
            line = name + "," + reservation.getItinerary().getFlightNumber();
            lines.add(line);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()));
        for(String writeLine:lines){
            writer.write(writeLine);
        }
        writer.close();
    }
}
