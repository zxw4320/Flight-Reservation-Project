package ui;

import database.CSVdb;
import database.Flightdb;
import database.ReservationCSVParser;
import database.Reservationdb;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import model.ReservationCollection;
import model.RouteMap;
import request.RequestHandler;

/**
 *
 */
public class Tui implements MultiSessionUI {
    
    private request.RequestHandler afrs;
    private SessionHandler sessionHandler;
    
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
        Flightdb flightdb = new CSVdb(a, w, f, d);
        Reservationdb reservationdb = new ReservationCSVParser(r);
        // use DB readers
        RouteMap routeMap = flightdb.generateRouteMap();
        ReservationCollection reservationCollection = reservationdb
            .generateReservationCollection(routeMap);
        // create request handler
        afrs = new RequestHandler(routeMap, reservationCollection);
        sessionHandler = new SessionHandler(this, afrs);
    }
    
    @Override
    public void printString(int cid, String printText) {
        if(printText.startsWith("connect")) {
            System.out.println(printText);
            sendString(cid + ",server,local;");
        } else {
            System.out.println(cid + "," + printText);
        }
    }
    
    private void sendString(String sendText) {
        sessionHandler.makeRequest(sendText);
    }
    
    public static void main(String[] args) {
        
        // ready TUI
        Tui activeTui = new Tui();
        Scanner input = new Scanner(System.in);
        
        // main program loop
        while (true) {
            // get input
            activeTui.sendString(input.nextLine());
        }
        
    }
    
}
