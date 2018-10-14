package ui;


import database.CSVdb;
import database.Flightdb;
import request.RequestHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Tui implements AFRSInterface {

    request.RequestHandler afrs;

    public Tui() {
        // acquire DB files
        Path a = Paths.get("csv/airports.csv");
        Path f = Paths.get("csv/flights.csv");
        Path w = Paths.get("csv/weather.csv");
        Path d = Paths.get("csv/delay.csv");
        Flightdb db = new CSVdb(a, w,f,d);
        afrs = new RequestHandler(db);
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
        while(true){
            inputLine = input.nextLine();
            activeTui.sendString(inputLine);
        }

    }

}
