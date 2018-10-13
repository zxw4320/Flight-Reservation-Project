package request;

public class AirportInfoRequest implements Request {

    private String airportCode;


    public AirportInfoRequest(String airportCode){
        this.airportCode = airportCode;
    }


    @Override
    public void execute() {

    }
}
