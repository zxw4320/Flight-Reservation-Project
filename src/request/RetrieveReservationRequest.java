package request;

import java.util.ArrayList;
import java.util.List;
import model.Airport;
import model.Reservation;
import model.ReservationCollection;
import model.RouteMap;
import ui.AFRSInterface;

/**
 * Retrieves reservations from the ReservationCollection given to it. Prints them all to the ui
 * given.
 */
public class RetrieveReservationRequest implements Request {
    
    private AFRSInterface ui;
    private ReservationCollection reservationCollection;
    private RouteMap routeMap;
    private String passengerName;
    private String originAirportCode;
    private String destinationAirportCode;
    
    
    /**
     * Constructor
     */
    public RetrieveReservationRequest(AFRSInterface ui, ReservationCollection
        reservationCollection, RouteMap routeMap, String passengerName
        , String originAirportCode, String destinationAirportCode) {
        this.reservationCollection = reservationCollection;
        this.ui = ui;
        this.routeMap = routeMap;
        this.passengerName = passengerName;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
    }
    
    
    @Override
    public void execute() {
        
        List<Reservation> reservations = new ArrayList<>();
        
        // error for origin
        if (!originAirportCode.equals("") &&
            routeMap.getAirport(originAirportCode) == null) {
            ui.printString("error,unknown origin");
            return;
        }
        
        // error for destination
        if (!destinationAirportCode.equals("") &&
            routeMap.getAirport(destinationAirportCode) == null) {
            ui.printString("error,unknown destination");
            return;
        }
        
        // loop over all reservations
        for (Reservation reservation : reservationCollection.listReservations()) {
            
            // check passenger
            boolean passengerMatch = reservation.getPassenger()
                .equals(passengerName);
            
            // check origin airport
            boolean originMatch = airportCodeEquals(reservation.getOrigin(),
                originAirportCode);
            
            // check destination airport
            boolean destinationMatch = airportCodeEquals(reservation
                .getDestination(), destinationAirportCode);
            
            // print if valid
            if (passengerMatch && originMatch && destinationMatch) {
                reservations.add(reservation);
            }
        }
        
        // print start line
        ui.printString("retrieve," + reservations.size());
        // print to ui
        reservations.forEach(r -> ui.printString(r.toString()));
        
    }
    
    /**
     * Little private helper method. We compare the code with the airport code. We also return true
     * when the input string is empty as it means no input
     *
     * @param airport airport to check
     * @param code Airport code to compare
     */
    private boolean airportCodeEquals(Airport airport, String code) {
        if (code.equals("")) {
            return true;
        }
        return airport.getAirportcode().equals(code);
    }
    
    @Override
    public boolean unexecute() {
        return false;
    }
}
