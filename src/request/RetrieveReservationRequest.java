package request;

import model.Airport;
import model.Reservation;
import model.ReservationCollection;
import ui.AFRSInterface;

/**
 *
 */
public class RetrieveReservationRequest implements Request {

    private AFRSInterface ui;
    private ReservationCollection reservationCollection;
    private String passengerName;
    private String originAirportCode;
    private String destinationAirportCode;


  /**
   * Constructor
   */
    public RetrieveReservationRequest(AFRSInterface ui, ReservationCollection
            reservationCollection, String passengerName, String
            originAirportCode, String destinationAirportCode){
        this.reservationCollection = reservationCollection;
        this.ui = ui;
        this.passengerName = passengerName;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
    }


    @Override
    public void execute() {
       for (Reservation reservation : reservationCollection.listReservations()){

           // check passenger
           boolean passengerMatch = reservation.getPassenger()
                   .equals(passengerName);

           // check origin airport
            boolean originMatch = airportCodeEquals(reservation.getOrigin(),
                    originAirportCode);

            // check destination airport
            boolean destinationMatch = airportCodeEquals(reservation
                    .getDestination(), destinationAirportCode);

            if (passengerMatch && originMatch && destinationMatch){
                ui.printString(reservation.toString());
            }
            else {
                ui.printString("Error: Reservation not found");
            }


       }
    }

    /**
     * Little private helper method.
     * null code string returns true.
     * Otherwise we compare the code with the airport code.
     *
     * @param airport airport to check
     * @param code Airport code to compare
     */
    private boolean airportCodeEquals(Airport airport, String code){
        if(code == null){
            return true;
        } else {
            return airport.getAirportcode().equals(code);
        }
    }
}
