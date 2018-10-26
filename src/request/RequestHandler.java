package request;

import model.ItineraryHistory;
import model.ReservationCollection;
import model.RouteMap;

import java.util.ArrayList;
import java.util.Arrays;
import request.FlightOrders.AirfareSort;
import request.FlightOrders.ArrivalSort;
import request.FlightOrders.DepartureSort;
import request.FlightOrders.FlightOrder;

/**
 * Handles all user input. Parses the input and calls the proper command, or prints the
 * proper error to the user. Fulfills the Invoker part of the Command design pattern.
 */
public class RequestHandler {

    // storage objects
    private RouteMap routeMap;
    private ReservationCollection reservationCollection;
    private ItineraryHistory itineraryHistory;
    private RequestHistory requestHistory;

    public RequestHandler(RouteMap routeMap, ReservationCollection reservationCollection){
        this.routeMap = routeMap;
        this.reservationCollection = reservationCollection;
        this.itineraryHistory = new ItineraryHistory();
        this.requestHistory = new RequestHistory();
    }

    /**
     * Handles taking in strings of input from a client. It takes arguments
     * separated by commas and terminated with a semi-colon. It stores the
     * previous part of the requestString and will accept more until it
     * receives the terminator.
     *
     * @param requestString User input string
     */
    public void makeRequest(ui.AFRSInterface ui, String requestString) {

        ArrayList<String> requestArray = new ArrayList<>();

        // split the string for parsing and remove terminator
        String[] tempArray = requestString
                .substring(0, requestString.length() - 1)
                .split(",");
        requestArray = new ArrayList<>(Arrays.asList(tempArray));

        // check the command we are running and hand off to associated method
        String command = requestArray.get(0);
        switch (command){
            case "info":        parseInfo(ui, requestArray);
                                break;
            case "reserve":     parseReserve(ui, requestArray);
                                break;
            case "retrieve":    parseRetrieve(ui, requestArray);
                                break;
            case "delete" :     parseDelete(ui, requestArray);
                                break;
            case "airport":     parseAirport(ui, requestArray);
                                break;
            case "undo":        requestHistory.undo(ui);
                                break;
            case "redo":        requestHistory.redo(ui);
                                break;
            default:            ui.printString("error,unknown request");
        }

    }

    /**
     * Checks for the sort order and generates a FlightInfoRequest with the proper
     * order or default if none is specified. Executes the request.
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseInfo(ui.AFRSInterface ui, ArrayList<String> argumentArray){

        Request flightInfoRequest;
        FlightOrder sortOrder = null;
        boolean validSort = true;

        // add 3rd argument if it does not exist
        if (argumentArray.size() < 4)
            argumentArray.add("");

        // Check if flight order was specified
        if(argumentArray.size() == 5) {
            switch (argumentArray.get(argumentArray.size() - 1)) {
                case "departure":   sortOrder = new DepartureSort();
                                    break;
                case "arrival":     sortOrder = new ArrivalSort();
                                    break;
                case "airfare":     sortOrder = new AirfareSort();
                                    break;
                default:            validSort = false;
                                    ui.printString("error,invalid sort order");
                                    break;
            }

        // set default flight order
        } else {
            sortOrder = new DepartureSort();
        }
        if(validSort) {
            // create request
            flightInfoRequest = new FlightInfoRequest(ui, routeMap, argumentArray, sortOrder, itineraryHistory);
            //execute request
            flightInfoRequest.execute();
        }
    }

    /**
     * Generates an MakeReservationRequest and executes it.
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseReserve(ui.AFRSInterface ui, ArrayList<String> argumentArray){

        int id = Integer.parseInt(argumentArray.get(1));
        String name = argumentArray.get(2);

        Request request = new MakeReservationRequest(ui, reservationCollection,
                itineraryHistory, id, name );
        request.execute();
        requestHistory.addRequest(ui, request);
    }

    /**
     * Generates an RetreiveReservationRequest and executes it.
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseRetrieve(ui.AFRSInterface ui, ArrayList<String> argumentArray){
        // add 3rd argument if it does not exist
        if (argumentArray.size() < 3)
            argumentArray.add("");

        // add 4rd argument if it does not exist
        if (argumentArray.size() < 4)
            argumentArray.add("");

        // create request
        Request retrieveRequest = new RetrieveReservationRequest(ui,
                reservationCollection, routeMap, argumentArray.get(1),
                argumentArray.get(2), argumentArray.get(3));

        // execute request
        retrieveRequest.execute();
    }

    /**
     * Generates an DeleteReservationRequest and executes it.
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseDelete(ui.AFRSInterface ui, ArrayList<String> argumentArray){
        Request deleteRequest = new DeleteReservationRequest(ui,
                argumentArray.get(1), argumentArray.get(2),
                argumentArray.get(3), reservationCollection);
        deleteRequest.execute();
        requestHistory.addRequest(ui, deleteRequest);
    }

    /**
     * Generates an AirportInfoRequest and executes it.
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseAirport(ui.AFRSInterface ui, ArrayList<String> argumentArray){
        // create request
        Request airportRequest = new AirportInfoRequest(ui, routeMap,
            argumentArray.get(1));
        // execute request
        airportRequest.execute();
    }
}
