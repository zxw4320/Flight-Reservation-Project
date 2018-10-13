package request;

public class DeleteReservationRequest implements Request {

    private String passengerName;
    private String originAirportCode;
    private String destinationAirportCode;


    public DeleteReservationRequest(String passengerName, String originAirportCode,
                             String destinationAirportCode){
        this.passengerName = passengerName;
        this.originAirportCode = originAirportCode;
        this.destinationAirportCode = destinationAirportCode;
    }


    @Override
    public void execute() {

    }
}
