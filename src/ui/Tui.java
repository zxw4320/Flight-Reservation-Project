package ui;

import database.CSVdb;
import database.Flightdb;
import database.ReservationCSVParser;
import database.Reservationdb;
import model.ReservationCollection;
import model.RouteMap;
import request.RequestHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 */
public class Tui implements AFRSInterface {

    request.RequestHandler afrs;

    /**
     * Constructor
     */
    public Tui() {
        // acquire DB files
        Path a = Paths.get("csv/airports.csv");
        Path f = Paths.get("csv/flights.csv");
        Path w = Paths.get("csv/weather.csv");
        Path d = Paths.get("csv/delay.csv");
        Path r = Paths.get("csv/reservations.csv");
        // make DB readers
        Flightdb flightdb = new CSVdb(a,w,f,d);
        Reservationdb reservationdb = new ReservationCSVParser(r);
        // use DB readers
        RouteMap routeMap = flightdb.generateRouteMap();
        ReservationCollection reservationCollection = reservationdb
                .generateReservationCollection(routeMap);
        // create request handler
        afrs = new RequestHandler(routeMap, reservationCollection);
    }

    @Override
    public void printString(String printText) {
        System.out.println(printText);
    }

    private void sendString(String sendText) {
        afrs.makeRequest(this, sendText);
    }

    public static void main(String[] args) {

        // ready TUI
        Tui activeTui = new Tui();
        Scanner input = new Scanner(System.in);
        String inputLine = "";
        boolean terminated;

        // main program loop
        while(true) {

            // input loop
            do {
                // get input
                inputLine += input.nextLine();

                // check for terminating character, partial request
                terminated = inputLine.endsWith(";");
                if (!terminated){
                    activeTui.printString("partial-request");
                }
                // loop until full request
            } while (!terminated);

            // send a command then clear buffer
            activeTui.sendString(inputLine);
            inputLine = "";
        }

    }

}
