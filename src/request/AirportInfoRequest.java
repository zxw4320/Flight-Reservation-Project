package request;

import ui.AFRSInterface;

public class AirportInfoRequest implements Request {

    private String airportCode;


    public AirportInfoRequest(AFRSInterface ui, String airportCode){
        this.airportCode = airportCode;

    }


    @Override
    public void execute() {

    }
}
