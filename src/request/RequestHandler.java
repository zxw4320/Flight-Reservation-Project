package request;

import database.Flightdb;
import itinerary.RouteMap;

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

    private String cachedString;
    private Boolean partialRequest;
    private RouteMap routeMap;
    private Flightdb db;

    public RequestHandler(Flightdb db){
        this.db = db;
        routeMap = db.generateRouteMap();
        cachedString = "";
        partialRequest = false;
    }

    /**
     * Handles taking in strings of input from a client. It takes arguments
     * separated by commas and terminated with a semi-colon. It returns a 1
     * when more information is needed from the user. It stores the previous
     * part of the requestString and will accept more until it receives the
     * terminator.
     * 
     * @param requestString User input string
     * @return 0 on success, 1 on error of partial request
     */
    public int makeRequest(ui.AFRSInterface ui, String requestString) {

        ArrayList<String> requestArray = new ArrayList<>();

        // use our cachedString, we prepend it if it exist TODO move to tui
        if (partialRequest) {
            ui.printString("partial-request");
            requestString = cachedString + requestString;
        }

        // check for terminating character
        boolean hasTerminator =
                requestString.charAt(requestString.length() - 1) == ';';

        // throw error on lack of terminator
        if (!hasTerminator) {
            cachedString += requestString;
            partialRequest = true;
            return 1;
        }
        // leave partial request state in it
        else if (partialRequest) {
            partialRequest = false;
            cachedString = "";
        }

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
            default:            ui.printString("error,unknown airport");
                                return 1;
        }

        // return success
        return 0;

    }

    /**
     * Parses through the argumentArray and checks to make sure all arguments are present
     * in order to run the command. Calls the FlightInfoRequest if all arguments are valid.
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseInfo(ui.AFRSInterface ui, ArrayList<String> argumentArray){

        Request flightInfoRequest;
        FlightOrder sortOrder = null;
        boolean validSort = true;

        // parts of the query that should always be present
        String origin = argumentArray.get(1);
        String destination = argumentArray.get(2);
        String numConnect = argumentArray.get(3);

        // check origin is valid
        if(routeMap.getAirport(origin) == null){
            ui.printString("error,unknown origin");

        // check destination is valid
        } else if(routeMap.getAirport(destination) == null){
            ui.printString("error,unknown destination");

        // check connection number is valid
        } else if(!numConnect.equals("0") && !numConnect.equals("1") &&
            !numConnect.equals("2") && !numConnect.equals("")){
            ui.printString("error,invalid connection limit");

        // Check if flight order was specified
        } else if(argumentArray.size() == 5) {
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
            flightInfoRequest = new FlightInfoRequest(ui, routeMap, argumentArray, sortOrder);
            //execute request
            flightInfoRequest.execute();
        }
    }

    /**
     *
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseReserve(ui.AFRSInterface ui, ArrayList<String> argumentArray){

    }

    /**
     *
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseRetrieve(ui.AFRSInterface ui, ArrayList<String> argumentArray){

    }

    /**
     *
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseDelete(ui.AFRSInterface ui, ArrayList<String> argumentArray){

    }

    /**
     *
     *
     * @param ui the user interface the command returns to
     * @param argumentArray arguments sent by the user
     */
    private void parseAirport(ui.AFRSInterface ui, ArrayList<String> argumentArray){
        if(routeMap.getAirport(argumentArray.get(1)) == null){ // check airport is valid
            ui.printString("error,unknown airport");
        } else {
            // create request
            Request airportRequest = new AirportInfoRequest(ui, routeMap,
                argumentArray.get(1));
            // execute request
            airportRequest.execute();
        }
    }
}
