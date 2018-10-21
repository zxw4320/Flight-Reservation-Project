package ui;

import database.*;
import model.*;
import request.*;

import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AFRSTest {

    RequestHandler requestHandler;
    FakeUI ui;

    @Before
    public void setup(){
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
        requestHandler = new RequestHandler(routeMap, reservationCollection);

        ui = new FakeUI();
    }

    @Test
    public void airportInfoTest(){
        List<String> responces = ui.sendCommand(requestHandler, "airport,BOS;");
        assertEquals("Boston (BOS) is currently partly cloudy, 70F with 30 minute delays.", responces.get(0));

    }

    @Test
    public void flightInfoTest(){
        List<String> responces = ui.sendCommand(requestHandler, "info,BOS,ATL;");
        System.out.println(responces);
        //TODO add asserts

    }


}
