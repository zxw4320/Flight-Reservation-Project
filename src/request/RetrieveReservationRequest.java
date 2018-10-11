package request;

public class RetrieveReservationRequest implements Request {

    private String passengerName;
    private String airportCode;


    public RetrieveReservationRequest(String passengerName){
        this.passengerName = passengerName;
    }

    public RetrieveReservationRequest(String passengerName, String airportCode){
        this.airportCode = airportCode;
    }


    @Override
    public void execute() {

    }
}
