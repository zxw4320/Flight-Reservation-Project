package request;

import itinerary.Flight;
import itinerary.Itinerary;
import itinerary.RouteMap;
import java.util.ArrayList;
import java.util.List;
import request.FlightOrders.FlightOrder;
import ui.AFRSInterface;

/**
 *  The request to gather all itineraries between the given airports that also comply
 *  with other arguments given. It is a Concrete Command in the Command pattern.
 */
public class FlightInfoRequest implements Request{

  private AFRSInterface ui;
  private RouteMap routeMap;
  private ArrayList<String> flightRequest;
  private FlightOrder sortOrder;

  /**
   * Constructor
   */
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
    result.append("info,").append(itineraries.size());
    // gathers all of the valid itineraries
    for(int i = 1; i <= itineraries.size(); i++){
     result.append("\n").append(i).append(",").append((itineraries.get(i - 1)).toString());
    }
    String fn = result.toString();
    fn = fn.substring(0, fn.length() - 1);
    ui.printString(fn);
  }

  /**
   * Helper method to generate Itineraries that fit all of the query information
   *
   * @return all itineraries that fit all the query requirements
   */
  private ArrayList<Itinerary> generateItineraries(List<Flight> flights){

    // parts of the request
    String origin = flightRequest.get(1);
    String destination = flightRequest.get(2);
    String numConnect = flightRequest.get(3);

    ArrayList<Itinerary> itineraries = new ArrayList<>();

    // puts all single flights into the list
    for(Flight flight : flights){
      if(flight.getOrigin().getAirportcode().equals(origin) &&
          flight.getDestination().getAirportcode().equals(destination)){
        ArrayList<Flight> temp = new ArrayList<>();
        temp.add(flight);
        itineraries.add(new Itinerary(temp));
      }
    }
   /* TODO fix this
    // puts all itineraries with 1 connection into the list
    if(numConnect.equals("1") || numConnect.equals("2") || numConnect.equals("")){
      ArrayList<Flight> origins = new ArrayList<>();
      ArrayList<Flight> dests = new ArrayList<>();

      // gather all flights that are not in the itinerary that start or end in the proper place
      for(Flight flight : flights){
        if(!flight.getOrigin().getAirportcode().equals(origin) ||
            !flight.getDestination().getAirportcode().equals(destination)) {
          if (flight.getOrigin().getAirportcode().equals(origin)) {
            origins.add(flight);
          }
          if(flight.getDestination().getAirportcode().equals(destination)){
            dests.add(flight);
          }
        }
      }

      for(Flight org : origins){
        for(Flight dest : dests){
          if(org.getDestination() == dest.getOrigin() &&
              org.getDestination().getDelaytime() < dest.getDepartureTime().convertTime() - org.getArrivalTime().convertTime()){

            ArrayList<Flight> temp = new ArrayList<>();
            temp.add(org);
            temp.add(dest);
            itineraries.add(new Itinerary(temp));
          }
        }
      }
    }*/

    // puts all itineraries with 2 connections into the list


    return itineraries;
  }
}
