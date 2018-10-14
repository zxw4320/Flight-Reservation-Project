package request;

import itinerary.Flight;
import itinerary.FlightInterface;
import itinerary.Itinerary;
import itinerary.RouteMap;
import java.util.ArrayList;
import java.util.Arrays;
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
    // creates all itineraries that fit the query
    ArrayList<Itinerary> itineraries = generateItineraries(routeMap.getFlights());
    itineraries = sortOrder.sortOrder(itineraries);
    StringBuilder result = new StringBuilder();
    result.append("info," + itineraries.size());
    // prints all of the valid itineraries
    for(int i = 1; i <= itineraries.size(); i++){
     result.append( "\n" + i + (itineraries.get(i - 1)).toString());
    }
    String fn = result.toString();
    fn = fn.substring(0, fn.length() - 1);
    ui.printString(fn);
  }

  /**
   *
   * @return all itineraries that fit all the query requirements
   */
  private ArrayList<Itinerary> generateItineraries(List<Flight> flights){
    ArrayList<Itinerary> itineraries = new ArrayList<>();
    for(Flight flight : flights){
      if(flight.getDestination().getName().equals(flightRequest.get(1)))
        itineraries.add(new Itinerary(new ArrayList<>(Arrays.asList(flight))));
    }

    return itineraries;
  }
}
