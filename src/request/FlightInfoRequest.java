package request;

import itinerary.Flight;
import itinerary.Itinerary;
import itinerary.RouteMap;
import java.util.ArrayList;
import java.util.List;
import request.FlightOrders.FlightOrder;
import ui.AFRSInterface;

public class FlightInfoRequest implements Request{

  private AFRSInterface ui;
  private RouteMap routeMap;
  private ArrayList<String> flightRequest;
  private FlightOrder sortOrder;

  public FlightInfoRequest(AFRSInterface ui, RouteMap routeMap,
                            ArrayList<String> flightRequest, FlightOrder sortOrder){
    this.ui = ui;
    this.routeMap = routeMap;
    this.flightRequest = flightRequest;
    this.sortOrder = sortOrder;
  }

  @Override
  public void execute() {
    List<Flight> flights = routeMap.getFlights();
    ArrayList<Itinerary> itineraries = new ArrayList<>();
    // creates all itineraries that fit the query
    //Todo put in the logic to sort everything

    ui.printString("info," + itineraries.size());
    // prints all of the valid itineraries
    for(int i = 1; i <= itineraries.size(); i++){
      ui.printString(i + (itineraries.get(i - 1)).toString());
    }
  }
}
