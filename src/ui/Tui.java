package ui;

import database.CSVdb;
import database.Flightdb;
import database.ReservationCSVParser;
import database.Reservationdb;
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
        // create request handler
        afrs = new RequestHandler(flightdb, reservationdb);
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
        while(true) {
            while(!inputLine.endsWith(";")) {
                inputLine += input.nextLine();
            }
            activeTui.sendString(inputLine);
            inputLine = "";
        }

    }

}
